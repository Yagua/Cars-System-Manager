package com.friendlyCarsSystem.friendly_cars.service;

import java.util.List;
import java.util.Optional;

import com.friendlyCarsSystem.friendly_cars.entity.Invoice;

import org.springframework.http.ResponseEntity;

/**
 * InvoiceService
 */
public interface InvoiceService {
    List<Invoice> getAllInvoices();
    Optional<Invoice> getInvoiceById(String invoiceId);
    Invoice createInvoice(Invoice invoice);
    Invoice updateInvoice(String invoiceId);
    Invoice partialUpdateInvoice(String invoiceId);
    ResponseEntity<String> deletInvoice(String invoiceId);
}
