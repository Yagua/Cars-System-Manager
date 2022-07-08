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
    List<Client> getAllClients();
    Client getClientById(String clientId) throws ClientNotFoundException;
    Client createClient(Client client);
    Client updateClient(String clientId, Client updatedClient) throws ClientNotFoundException;
    Client partialUpdateClient(String clientId, Map<Object, Object> fields) throws ClientNotFoundException;
    ResponseEntity<String> deletClient(String clientId) throws ClientNotFoundException;
    //authoritation
    Client loginClient(String clientName, String clientPassword) throws ClientNotFoundException;
    Client changePassword(String clientName, String clientPassword) throws ClientNotFoundException;
}
