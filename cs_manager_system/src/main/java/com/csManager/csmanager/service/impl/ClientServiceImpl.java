package com.csManager.csmanager.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.csManager.csmanager.entity.Client;
import com.csManager.csmanager.entity.Invoice;
import com.csManager.csmanager.entity.ShoppingCart;
import com.csManager.csmanager.exception.ClientNotFoundException;
import com.csManager.csmanager.repository.ClientRepository;
import com.csManager.csmanager.service.ClientService;

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
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return ResponseEntity.ok(clients);
    }

    @Override
    public ResponseEntity<Client> getClientById(String clientId)
        throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with '%s' not found",
                            clientId)));
        return ResponseEntity.ok(client);
    }

    @Override
    public ResponseEntity<Client> createClient(Client client) {
        ShoppingCart cart = client.getShoppingCart();

        if(cart == null) client.setShoppingCart(new ShoppingCart());

        cart.setClient(client);
        List<Invoice> invoices = client.getInvoices();
        invoices.forEach(invoice -> {
            invoice.setClient(client);
        });

        Client savedClient = clientRepository.save(client);

        return ResponseEntity.ok(savedClient);
    }

    @Override
    public ResponseEntity<Client> updateClient(String clientId, Client updatedClient)
        throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with '%s' not found",
                            clientId)));

        // searchedClient.setClientId(client.getClientId());
        client.setShoppingCart(updatedClient.getShoppingCart());
        client.setAddress(updatedClient.getAddress());
        client.setPassword(updatedClient.getPassword());
        client.setUserName(updatedClient.getUserName());
        client.setFirstName(updatedClient.getFirstName());
        client.setSecondName(updatedClient.getSecondName());
        client.setPaternalLastName(updatedClient.getPaternalLastName());
        client.setMaternalLastName(updatedClient.getMaternalLastName());
        client.setTelephoneNumber(updatedClient.getTelephoneNumber());

        Client savedClient = clientRepository.save(client);

        return ResponseEntity.ok(savedClient);
    }

    @Override
    public ResponseEntity<Client> partialUpdateClient(String clientId, Map<Object, Object> fields)
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

        Client savedClient = clientRepository.save(client);

        return ResponseEntity.ok(savedClient);
    }

    @Override
    public ResponseEntity<?> deletClient(String clientId)
        throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with '%s' not found",
                            clientId)));
        clientRepository.delete(client);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Client> loginClient(String clientName, String clientPassword)
        throws ClientNotFoundException {
        Client client = clientRepository.findByUserNameAndPassword(clientName,
                clientPassword).orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with user name '%s' not found",
                            clientName)));
        return ResponseEntity.ok(client);
    }

    @Override
    public ResponseEntity<Client> changePassword(String clientName, String clientPassword)
        throws ClientNotFoundException {
        Client client = clientRepository.findByUserName(clientName)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with user name '%s' not found",
                            clientName)));
        client.setPassword(clientPassword);

        Client savedClient = clientRepository.save(client);
        return ResponseEntity.ok(savedClient);
    }
}
