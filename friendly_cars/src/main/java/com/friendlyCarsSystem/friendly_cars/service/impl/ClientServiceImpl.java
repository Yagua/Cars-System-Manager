package com.friendlyCarsSystem.friendly_cars.service.impl;

import java.util.List;
import java.util.Optional;

import com.friendlyCarsSystem.friendly_cars.entity.Client;
import com.friendlyCarsSystem.friendly_cars.repository.ClientRepository;
import com.friendlyCarsSystem.friendly_cars.service.ClientService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * ClientServiceImpl
 */
@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Client> getClientById(String clientId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Client createClient(Client client) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Client updateClient(String clientId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Client partialUpdateClient(String clientId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<String> deletClient(String clientId) {
        // TODO Auto-generated method stub
        return null;
    }
}
