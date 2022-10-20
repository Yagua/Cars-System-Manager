package com.csManager.csmanager.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.csManager.csmanager.entity.Client;
import com.csManager.csmanager.entity.Invoice;
import com.csManager.csmanager.exception.ClientNotFoundException;
import com.csManager.csmanager.exception.InvoiceNotFoundException;
import com.csManager.csmanager.repository.ClientRepository;
import com.csManager.csmanager.repository.InvoiceRepository;
import com.csManager.csmanager.service.InvoiceService;

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
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return ResponseEntity.ok(invoices);
    }

    @Override
    public ResponseEntity<List<Invoice>> getAllInvoicesByClientId(String clientId)
        throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with '%s' not found",
                            clientId)));
        List<Invoice> clientInvoices = client.getInvoices();
        return ResponseEntity.ok(clientInvoices);
    }

    @Override
    public ResponseEntity<Invoice> getInvoiceById(long invoiceId)
        throws InvoiceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceId)
            .orElseThrow(() -> new InvoiceNotFoundException(
                        String.format("Invoice identified with '%s' not found",
                            invoiceId)));
        return ResponseEntity.ok(invoice);
    }

    @Override
    public ResponseEntity<Invoice> createInvoice(Invoice invoice, String clientId)
        throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with '%s' not found",
                            clientId)));

        invoice.setClient(client);

        Invoice savedInvoice = invoiceRepository.save(invoice);

        return ResponseEntity.ok(savedInvoice);
    }

    @Override
    public ResponseEntity<Invoice> updateInvoice(long invoiceId, Invoice updatedInvoice)
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
        invoice.setVehicles(updatedInvoice.getVehicles());

        Invoice savedInvoice = invoiceRepository.save(invoice);

        return ResponseEntity.ok(savedInvoice);
    }

    @Override
    public ResponseEntity<Invoice> partialUpdateInvoice(long invoiceId, Map<Object, Object> fields)
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

        Invoice savedInvoice = invoiceRepository.save(invoice);

        return ResponseEntity.ok(savedInvoice);
    }

    @Override
    public ResponseEntity<?> deletInvoice(long invoiceId)
        throws InvoiceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceId)
            .orElseThrow(() -> new InvoiceNotFoundException(
                        String.format("Invoice identified with '%s' not found",
                            invoiceId)));
        invoiceRepository.delete(invoice);
        return ResponseEntity.noContent().build();
    }

}
