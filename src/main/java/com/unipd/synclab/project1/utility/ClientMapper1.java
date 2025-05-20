package com.unipd.synclab.project1.utility; 
// import com.unipd.synclab.project1.dto.AddressDTO;
// import com.unipd.synclab.project1.dto.ClientRequestDTO;
// import com.unipd.synclab.project1.dto.ClientResponseDTO;
// import com.unipd.synclab.project1.model.Address;
// import com.unipd.synclab.project1.model.Client;
// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
// import org.mapstruct.MappingTarget; // Per metodi di update
// // import org.mapstruct.factory.Mappers;

// @Mapper(componentModel = "spring") // Dice a MapStruct di generare un bean Spring
public interface ClientMapper1 {

    // Istanza per poterla usare se non si usa @Autowired (opzionale con componentModel="spring")
    // ClientMapStructMapper INSTANCE = Mappers.getMapper(ClientMapStructMapper.class);

    // // --- Address Mapping ---
    // AddressDTO addressToAddressDTO(Address address);
    // Address addressDTOToAddress(AddressDTO addressDTO);

    // // --- Client Mapping ---
    // // Se i nomi dei campi sono gli stessi, MapStruct li mappa automaticamente.
    // // Se 'address' in Client deve essere mappato a 'address' in ClientResponseDTO,
    // // e hai già i metodi per mappare Address <-> AddressDTO, MapStruct li userà.
    // ClientResponseDTO clientToClientResponseDTO(Client client);

    // // Per il mapping da ClientRequestDTO a Client, se ci sono campi con nomi diversi
    // // o logica speciale, puoi usare @Mapping.
    // // Ad esempio, se ClientRequestDTO non ha 'id' ma Client sì (e non deve essere settato qui).
    // // MapStruct di default ignora i campi sorgente che non hanno un target corrispondente.
    // // @Mapping(target = "reservations", ignore = true) // Se avessi altre relazioni
    // // @Mapping(target = "profile", ignore = true)
    // @Mapping(target = "id", ignore = true) // Non mappare l'ID quando crei da DTO
    // Client clientRequestDTOToClient(ClientRequestDTO clientRequestDTO);

    // // Metodo per aggiornare un'entità esistente da un DTO
    // // @MappingTarget dice a MapStruct di aggiornare l'istanza 'client' passata
    // // invece di crearne una nuova.
    // @Mapping(target = "id", ignore = true) // Non aggiornare l'ID dall'DTO
    // void updateClientFromDto(ClientRequestDTO dto, @MappingTarget Client client);

    // @Mapping(target = "id", ignore = true) // Non aggiornare l'ID dall'DTO
    // void updateAddressFromDto(AddressDTO dto, @MappingTarget Address address);
}