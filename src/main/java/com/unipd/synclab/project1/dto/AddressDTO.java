package com.unipd.synclab.project1.dto;

import lombok.Data; // O @Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor individualmente
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data // Include @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Integer id; // Utile se vuoi permettere di associare a un indirizzo esistente o per la risposta

    @NotBlank(message = "Road cannot be blank")
    @Size(max = 255, message = "Road name cannot exceed 255 characters")
    private String road;

    @NotBlank(message = "City cannot be blank")
    @Size(max = 100, message = "City name cannot exceed 100 characters")
    private String city;
}