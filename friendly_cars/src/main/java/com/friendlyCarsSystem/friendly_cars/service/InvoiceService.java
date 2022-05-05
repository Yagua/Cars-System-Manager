package com.friendlyCarsSystem.friendly_cars.service;

import java.util.List;
import java.util.Map;

import com.friendlyCarsSystem.friendly_cars.entity.Invoice;
import com.friendlyCarsSystem.friendly_cars.exception.InvoiceNotFoundException;

import org.springframework.http.ResponseEntity;

/**
 * InvoiceService
 */
public interface InvoiceService {
    List<Invoice> getAllInvoices();
    Invoice getInvoiceById(String invoiceId) throws InvoiceNotFoundException;
    Invoice createInvoice(Invoice invoice);
    Invoice updateInvoice(String invoiceId, Invoice invoice) throws InvoiceNotFoundException;
    Invoice partialUpdateInvoice(String invoiceId, Map<Object, Object> fields) throws InvoiceNotFoundException;
    ResponseEntity<String> deletInvoice(String invoiceId) throws InvoiceNotFoundException;;
}
