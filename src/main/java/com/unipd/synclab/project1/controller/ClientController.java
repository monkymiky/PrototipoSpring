package com.unipd.synclab.project1.controller;

import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unipd.synclab.project1.dto.ClientRequestDTO;
import com.unipd.synclab.project1.dto.ClientResponseDTO;
import com.unipd.synclab.project1.service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients") 
public class ClientController {
    
    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }
    @GetMapping("/{clientId}")
    public ClientResponseDTO getClient(@PathVariable("clientId") Integer clientId) throws Exception {
        ClientResponseDTO  clientDTO =  clientService.getClientbyId(clientId);
        return clientDTO;
    }

    @GetMapping
    public List<ClientResponseDTO> getMethodName() {
        return clientService.getAllClients();
    }
    
    @PostMapping
    public ResponseEntity<ClientResponseDTO> addClient(@Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO savedClient = clientService.addClient(clientRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }
    
    @PutMapping("/{clientId}")
    public ClientResponseDTO editClient(@Valid @RequestBody ClientRequestDTO clientRequestDTO, @PathVariable("clientId") Integer clientId) throws Exception{
        return clientService.editClient(clientId,clientRequestDTO);
    }
    
    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable("clientId") Integer clientId) {
        clientService.deleteClient(clientId);
    }
    
}
