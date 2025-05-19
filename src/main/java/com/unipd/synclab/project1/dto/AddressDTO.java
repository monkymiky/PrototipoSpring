package com.unipd.synclab.project1.dto;

import lombok.Data; // O @Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor individualmente
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Include @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Integer id; // Utile se vuoi permettere di associare a un indirizzo esistente o per la risposta
    private String road;
    private String city;
}