package com.unipd.synclab.project1.utility;

import com.unipd.synclab.project1.dto.AddressDTO;
import com.unipd.synclab.project1.dto.ClientRequestDTO;
import com.unipd.synclab.project1.dto.ClientResponseDTO;
import com.unipd.synclab.project1.model.Address;
import com.unipd.synclab.project1.model.Client;
import org.springframework.stereotype.Component;

@Component // Se vuoi iniettarlo, altrimenti metodi statici
public class ClientMapper {

    // --- Address Mapper ---
    public AddressDTO toAddressDTO(Address address) {
        if (address == null) {
            return null;
        }
        return new AddressDTO(
                address.getId(),
                address.getRoad(),
                address.getCity()
        );
    }

    public Address toAddressEntity(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }
        Address address = new Address();
        // NON impostare l'ID se vuoi creare un nuovo indirizzo.
        // Se addressDTO.getId() != null, potresti voler caricare un indirizzo esistente.
        // Per ora, assumiamo che si crei sempre un nuovo indirizzo o si aggiorni quello esistente.
        address.setId(addressDTO.getId()); // Attenzione qui: vedi nota nel service
        address.setRoad(addressDTO.getRoad());
        address.setCity(addressDTO.getCity());
        return address;
    }


    // --- Client Mapper ---
    public ClientResponseDTO toClientResponseDTO(Client client) {
        if (client == null) {
            return null;
        }
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setAddress(toAddressDTO(client.getAddress())); // Usa il mapper dell'indirizzo
        // Mappa altri campi come profile, reservations se necessario
        return dto;
    }

    public Client toClientEntity(ClientRequestDTO clientRequestDTO) {
        if (clientRequestDTO == null) {
            return null;
        }
        Client client = new Client();
        client.setName(clientRequestDTO.getName());
        client.setEmail(clientRequestDTO.getEmail());

        // Gestione dell'indirizzo: crea una nuova entità Address dal DTO
        if (clientRequestDTO.getAddress() != null) {
            Address addressEntity = toAddressEntity(clientRequestDTO.getAddress());
            client.setAddress(addressEntity); // Questo chiamerà il tuo setter custom in Client
                                              // che dovrebbe sincronizzare address.getClients().add(client)
        }
        return client;
    }

    // Potresti aver bisogno di un metodo per aggiornare un'entità esistente da un DTO
    public void updateClientFromDTO(Client client, ClientRequestDTO clientRequestDTO) {
        client.setName(clientRequestDTO.getName());
        client.setEmail(clientRequestDTO.getEmail());

        // Gestione dell'aggiornamento dell'indirizzo
        if (clientRequestDTO.getAddress() != null) {
            AddressDTO addressDTO = clientRequestDTO.getAddress();
            Address currentAddress = client.getAddress();

            if (currentAddress == null) {
                currentAddress = new Address();
            }
            // Se l'address DTO ha un ID e corrisponde all'ID dell'indirizzo corrente, aggiorniamo.
            // Altrimenti, potresti decidere di creare un nuovo indirizzo o cercare uno esistente.
            // Per semplicità qui aggiorniamo/creiamo.
            currentAddress.setId(addressDTO.getId()); // Potrebbe essere null se si crea un nuovo indirizzo
            currentAddress.setRoad(addressDTO.getRoad());
            currentAddress.setCity(addressDTO.getCity());
            client.setAddress(currentAddress); // Chiama il setter per la sincronizzazione
        } else {
            client.setAddress(null); // Se l'indirizzo viene rimosso
        }
    }
}