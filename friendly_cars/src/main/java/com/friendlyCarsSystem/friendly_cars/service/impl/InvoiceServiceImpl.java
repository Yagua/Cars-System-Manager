package com.friendlyCarsSystem.friendly_cars.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.friendlyCarsSystem.friendly_cars.entity.Client;
import com.friendlyCarsSystem.friendly_cars.entity.Invoice;
import com.friendlyCarsSystem.friendly_cars.entity.Vehicle;
import com.friendlyCarsSystem.friendly_cars.exception.ClientNotFoundException;
import com.friendlyCarsSystem.friendly_cars.exception.InvoiceNotFoundException;
import com.friendlyCarsSystem.friendly_cars.repository.ClientRepository;
import com.friendlyCarsSystem.friendly_cars.repository.InvoiceRepository;
import com.friendlyCarsSystem.friendly_cars.service.InvoiceService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

/**
 * InvoiceServiceImpl
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;
    private ClientRepository clientRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository,
            ClientRepository clientRepository) {
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public List<Invoice> getAllInvoicesByClientId(String clientId)
        throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with '%s' not found",
                            clientId)));
        return client.getInvoices();
    }

    @Override
    public Invoice getInvoiceById(long invoiceId)
        throws InvoiceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceId)
            .orElseThrow(() -> new InvoiceNotFoundException(
                        String.format("Invoice identified with '%s' not found",
                            invoiceId)));
        return invoice;
    }

    @Override
    public Invoice createInvoice(Invoice invoice, String clientId)
        throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with '%s' not found",
                            clientId)));

        List<Vehicle> vehicles = invoice.getVehicles();
        vehicles.forEach(vehicle -> vehicle.setInvoice(invoice));

        List<Invoice> invoices = client.getInvoices();
        invoices.add(invoice);
        invoice.setClient(client);

        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice updateInvoice(long invoiceId, Invoice updatedInvoice)
        throws InvoiceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceId)
            .orElseThrow(() -> new InvoiceNotFoundException(
                        String.format("Invoice identified with '%s' not found",
                            invoiceId)));

        // searchedInvoice.setInvoiceId(invoice.getInvoiceId());
        // searchedInvoice.setClient(invoice.getClient());
        invoice.setDiscount(updatedInvoice.getDiscount());
        invoice.setDateOfSale(updatedInvoice.getDateOfSale());
        invoice.setTotalPrice(updatedInvoice.getTotalPrice());
        invoice.setAditionalPrices(updatedInvoice.getAditionalPrices());

        List<Vehicle> vehicles = updatedInvoice.getVehicles();
        vehicles.forEach(vehicle -> vehicle.setInvoice(invoice));

        invoice.setVehicles(updatedInvoice.getVehicles());

        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice partialUpdateInvoice(long invoiceId, Map<Object, Object> fields)
        throws InvoiceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceId)
            .orElseThrow(() -> new InvoiceNotFoundException(
                        String.format("Invoice identified with '%s' not found",
                            invoiceId)));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Invoice.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, invoice, value);
        });

        return invoiceRepository.save(invoice);
    }

    @Override
    public ResponseEntity<String> deletInvoice(long invoiceId)
        throws InvoiceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceId)
            .orElseThrow(() -> new InvoiceNotFoundException(
                        String.format("Invoice identified with '%s' not found",
                            invoiceId)));
        invoiceRepository.delete(invoice);
        return ResponseEntity.ok(String.format("Invoice '%d' deleted", invoiceId));
    }

}
