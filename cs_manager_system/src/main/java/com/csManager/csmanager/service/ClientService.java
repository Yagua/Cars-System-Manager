package com.csManager.csmanager.service;

import java.util.List;
import java.util.Map;

import com.csManager.csmanager.entity.Client;
import com.csManager.csmanager.exception.ClientNotFoundException;

import org.springframework.http.ResponseEntity;

/**
 * ClientService
 */
public interface ClientService {
    ResponseEntity<List<Client>> getAllClients();
    ResponseEntity<Client> getClientById(String clientId) throws ClientNotFoundException;
    ResponseEntity<Client> createClient(Client client);
    ResponseEntity<Client> updateClient(String clientId, Client updatedClient) throws ClientNotFoundException;
    ResponseEntity<Client> partialUpdateClient(String clientId, Map<Object, Object> fields) throws ClientNotFoundException;
    ResponseEntity<?> deletClient(String clientId) throws ClientNotFoundException;
    //authoritation
    ResponseEntity<Client> loginClient(String clientName, String clientPassword) throws ClientNotFoundException;
    ResponseEntity<Client> changePassword(String clientName, String clientPassword) throws ClientNotFoundException;
}
