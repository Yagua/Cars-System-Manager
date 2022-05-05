package com.friendlyCarsSystem.friendly_cars.service;

import java.util.List;
import java.util.Map;

import com.friendlyCarsSystem.friendly_cars.entity.Client;
import com.friendlyCarsSystem.friendly_cars.exception.ClientNotFoundException;

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
}
