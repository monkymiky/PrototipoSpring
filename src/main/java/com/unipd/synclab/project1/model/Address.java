package com.unipd.synclab.project1.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String road;
    private String city;

    @OneToMany(mappedBy = "address")
    @JsonBackReference("client-address-reference") // quando faccio getClient() serializza il client con il suo address omettendo di serializzare la lista di clients 
    private List<Client> clients = new ArrayList<>();

    public void addClient(Client client) {
        clients.add(client);
        client.setAddress(this); // Sincronizza l'altro lato della relazione
    }

    public void removeClient(Client client) {
        clients.remove(client);
        client.setAddress(null); // Sincronizza l'altro lato della relazione
    }
    void internalAddClient(Client client){
        this.clients.add(client);
    }
    void internalRemoveClient(Client client) {
        this.clients.remove(client);
    }
}
