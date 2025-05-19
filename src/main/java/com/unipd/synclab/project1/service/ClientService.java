package com.unipd.synclab.project1.service;

import com.unipd.synclab.project1.dto.AddressDTO;
import com.unipd.synclab.project1.dto.ClientRequestDTO;
import com.unipd.synclab.project1.dto.ClientResponseDTO;
import com.unipd.synclab.project1.exception.ReferencedResourceNotFoundException;
import com.unipd.synclab.project1.utility.ClientMapper;
import com.unipd.synclab.project1.model.Address;
import com.unipd.synclab.project1.model.Client;
import com.unipd.synclab.project1.repository.AddressRepository; 
import com.unipd.synclab.project1.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    // inserito come campo statico e aggiunto nel costruttore per implementare la constructor injection per migliore testabilità e
    // identificazione delle dipendenze
    private final ClientRepository clientRepository; 
    private final AddressRepository addressRepository;
    private final ClientMapper clientMapper;

    // @Autowired  Opzionale se c'è un solo costruttore
    public ClientService(ClientRepository clientRepository,
                         AddressRepository addressRepository, 
                         ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository; 
        this.clientMapper = clientMapper;
    }

    @Transactional(readOnly = true)
    public  ClientResponseDTO getClientbyId(Integer id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + id));
        return clientMapper.toClientResponseDTO(client);
    }

    @Transactional(readOnly = true)
    public List<ClientResponseDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toClientResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClientResponseDTO addClient(ClientRequestDTO clientRequestDTO) {
        Client client = clientMapper.toClientEntity(clientRequestDTO);

        // Gestione dell'Address:
        // Se l'AddressDTO ha un ID, potremmo volerlo associare a un indirizzo esistente.
        // Se non ha ID (o ID=0), ne creiamo uno nuovo.
        Address addressToSave = client.getAddress();
        if (addressToSave != null) {
            Integer addressId = addressToSave.getId();
            boolean addressExsist = (addressId != null && addressId != 0);
            if (addressExsist) {
                // Prova a caricare l'indirizzo esistente
                Address existingAddress = addressRepository.findById(addressId)
                    .orElseThrow(() -> new ReferencedResourceNotFoundException("Attempted to reference a non-existent Address with id: " + addressId)); // O lancia eccezione perche DEVE esistere
                // ID trovato, viene utilizzato l'indirizzo esistente
                addressToSave = existingAddress;
            } else {
                addressToSave.setId(null);
            }
        }
        client.setAddress(addressToSave); // Riassegna per assicurare che il client abbia l'istanza corretta (modificata o nuova)

        Client savedClient = clientRepository.save(client);
        return clientMapper.toClientResponseDTO(savedClient);
    }


    @Transactional
    public ClientResponseDTO editClient(Integer clientId, ClientRequestDTO clientRequestDTO) {
        Client clientToUpdate = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + clientId));

        // Mappa i campi da DTO a entità
        // Questa logica può diventare complessa, specialmente per le relazioni
        clientToUpdate.setName(clientRequestDTO.getName());
        clientToUpdate.setEmail(clientRequestDTO.getEmail());

        AddressDTO addressDTO = clientRequestDTO.getAddress();
        if (addressDTO != null) {
            boolean isNewAddress = addressDTO.getId() == null || addressDTO.getId() == 0;
            if (!isNewAddress) {
                // L'utente vuole aggiornare/associare a un indirizzo esistente
                Address addressFromRepo = addressRepository.findById(addressDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + addressDTO.getId() + " for update"));
                // Aggiorna i campi dell'indirizzo trovato con quelli del DTO
                if(addressFromRepo.getClients().size() > 1){
                    Address newAddress = clientMapper.toAddressEntity(addressDTO);
                    newAddress.setId(null);
                    clientToUpdate.setAddress(newAddress);
                }
                else{
                    addressFromRepo.setRoad(addressDTO.getRoad());
                    addressFromRepo.setCity(addressDTO.getCity());
                    clientToUpdate.setAddress(addressFromRepo);
                }
            } else {
                // L'utente vuole creare un nuovo indirizzo per questo client
                Address newAddress = clientMapper.toAddressEntity(addressDTO);
                newAddress.setId(null); // Assicura sia nuovo
                // addressRepository.save(newAddress); // Salva esplicitamente SE NON C'È CASCADE da Client ad Address
                                                    // o se vuoi essere sicuro che sia già persistito
                clientToUpdate.setAddress(newAddress); // Usa il setter per la sincronizzazione
            }
        } else {
            // L'utente vuole rimuovere l'associazione all'indirizzo
            clientToUpdate.setAddress(null); // Il setter dovrebbe gestire la rimozione dalla lista clients del vecchio indirizzo
        }

        Client updatedClient = clientRepository.save(clientToUpdate);
        return clientMapper.toClientResponseDTO(updatedClient);
    }

    @Transactional
    public void deleteClient(Integer id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }
}
