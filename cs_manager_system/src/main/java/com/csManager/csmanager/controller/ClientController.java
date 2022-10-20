package com.csManager.csmanager.controller;

import java.util.List;
import java.util.Map;

import com.csManager.csmanager.entity.Client;
import com.csManager.csmanager.service.ClientService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClientController
 */
@RestController
@RequestMapping("/api/v1/clients")
@CrossOrigin
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable String clientId) {
        return clientService.getClientById(clientId);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable String clientId,
            @RequestBody Client client) {
        return clientService.updateClient(clientId, client);
    }

    @PatchMapping("/{clientId}")
    public ResponseEntity<Client> partialUpdateClient(@PathVariable String clientId,
            @RequestBody Map<Object, Object> fields) {
        return clientService.partialUpdateClient(clientId, fields);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable String clientId) {
        return clientService.deletClient(clientId);
    }
}
