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
        ShoppingCart cart = client.getShoppingCart();

        if(cart == null) client.setShoppingCart(new ShoppingCart());

        cart.setClient(client);
        List<Invoice> invoices = client.getInvoices();
        invoices.forEach(invoice -> {
            invoice.setClient(client);
        });

        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(String clientId, Client updatedClient)
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

        return clientRepository.save(client);
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

    @Override
    public Client loginClient(String clientName, String clientPassword)
        throws ClientNotFoundException {
        Client client = clientRepository.findByUserNameAndPassword(clientName,
                clientPassword).orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with user name '%s' not found",
                            clientName)));
        return client;
    }

    @Override
    public Client changePassword(String clientName, String clientPassword)
        throws ClientNotFoundException {
        Client client = clientRepository.findByUserName(clientName)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with user name '%s' not found",
                            clientName)));
        client.setPassword(clientPassword);

        return clientRepository.save(client);
    }
}
