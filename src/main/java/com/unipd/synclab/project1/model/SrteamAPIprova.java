package com.unipd.synclab.project1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrteamAPIprova {
    private String descrizione;
    private Boolean completato;
    private Integer priorità;
}
