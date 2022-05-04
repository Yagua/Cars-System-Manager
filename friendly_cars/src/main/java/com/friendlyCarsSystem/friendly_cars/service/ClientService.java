package com.friendlyCarsSystem.friendly_cars.service;

import java.util.List;
import java.util.Optional;

import com.friendlyCarsSystem.friendly_cars.entity.Client;

import org.springframework.http.ResponseEntity;

/**
 * ClientService
 */
public interface ClientService {
    List<Client> getAllClients();
    Optional<Client> getClientById(String clientId);
    Client createClient(Client client);
    Client updateClient(String clientId);
    Client partialUpdateClient(String clientId);
    ResponseEntity<String> deletClient(String clientId);
}
