package com.unipd.synclab.project1.controller;

import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unipd.synclab.project1.dto.ClientRequestDTO;
import com.unipd.synclab.project1.dto.ClientResponseDTO;
import com.unipd.synclab.project1.service.ClientService;


@RestController
@RequestMapping("/client") 
public class ClientController {
    
    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }
    @GetMapping("/{clientId}")
    public ClientResponseDTO getClient(@PathVariable("clientId") Integer clientId) throws Exception {
        ClientResponseDTO  clientDTO =  clientService.getClientbyId(clientId);
        if(clientDTO != null) 
            return clientDTO;
        throw new Exception("Utente non trovato nella lista con id = " + clientId);
    }

    @GetMapping("/all")
    public List<ClientResponseDTO> getMethodName() {
        return clientService.getAllClients();
    }
    
    @PostMapping
    public ClientResponseDTO addClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        return clientService.addClient(clientRequestDTO);
    }
    
    @PutMapping("/{clientId}")
    public ClientResponseDTO editClient(@RequestBody ClientRequestDTO clientRequestDTO, @PathVariable("clientId") Integer clientId) throws Exception{
        return clientService.editClient(clientId,clientRequestDTO);
    }
    
    @DeleteMapping("/{clientId}")
    public void deleteClient(@PathVariable("clientId") Integer clientId){
        clientService.deleteClient(clientId);
    }
    
}
