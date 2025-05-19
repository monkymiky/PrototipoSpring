package com.unipd.synclab.project1.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // normalmente si trasforma l'entity Client in un DTO e poi si serializza quello ma questa è la soluzione più veloce
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="address_id", nullable = false)
    @JsonManagedReference("client-address-reference") // quando faccio get client serializza il client con il suo address
    private Address address; 

    // @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // private List<Reservation> reservations;

    // @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, optional = false)
    // private Profile profile;

    public void setAddress(Address address) {
        // Rimuovi questo client dalla lista del vecchio indirizzo (se presente)
        if (this.address != null) {
            this.address.internalRemoveClient(this);
        }
        this.address = address;
        // Aggiungi questo client alla lista del nuovo indirizzo (se presente)
        if (address != null) {
            address.internalAddClient(this);
        }
    }
}
