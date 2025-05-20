package com.unipd.synclab.project1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data; // O @Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor individualmente
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data // Include @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Integer id; // Utile se vuoi permettere di associare a un indirizzo esistente o per la risposta
    @NotBlank(message = "Road cannot be blank")
    @Size(min = 2, max = 300, message = "Road must be between 2 and 300 characters")
    @NotNull(message = "Road cannot be null")
    private String road;
    @NotBlank(message = "City cannot be blank")
    @Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
    @NotNull(message = "City cannot be null")
    private String city;
}