package com.friendlyCarsSystem.friendly_cars.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.friendlyCarsSystem.friendly_cars.entity.Client;
import com.friendlyCarsSystem.friendly_cars.entity.Invoice;
import com.friendlyCarsSystem.friendly_cars.exception.ClientNotFoundException;
import com.friendlyCarsSystem.friendly_cars.repository.ClientRepository;
import com.friendlyCarsSystem.friendly_cars.service.ClientService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

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
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(String clientId)
        throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with '%s' not found",
                            clientId)));
        return client;
    }

    @Override
    public Client createClient(Client client) {
        Invoice invoice = client.getInvoice();
        invoice.setClient(client);
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(String clientId, Client client)
        throws ClientNotFoundException {
        Client searchedClient = clientRepository.findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with '%s' not found",
                            clientId)));

        // searchedClient.setInvoice(client.getInvoice());
        // searchedClient.setClientId(client.getClientId());
        searchedClient.setAddress(client.getAddress());
        searchedClient.setPassword(client.getPassword());
        searchedClient.setUserName(client.getUserName());
        searchedClient.setFirstName(client.getFirstName());
        searchedClient.setSecondName(client.getSecondName());
        searchedClient.setPaternalLastName(client.getPaternalLastName());
        searchedClient.setMaternalLastName(client.getMaternalLastName());
        searchedClient.setTelephoneNumber(client.getTelephoneNumber());

        return clientRepository.save(searchedClient);
    }

    @Override
    public Client partialUpdateClient(String clientId, Map<Object, Object> fields)
        throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with '%s' not found",
                            clientId)));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Client.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, client, value);
        });

        return clientRepository.save(client);
    }

    @Override
    public ResponseEntity<String> deletClient(String clientId)
        throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with '%s' not found",
                            clientId)));
        clientRepository.delete(client);
        return ResponseEntity.ok(String.format("Client '%s' deleted", clientId));
    }
}
