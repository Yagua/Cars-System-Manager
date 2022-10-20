package com.csManager.csmanager.service;

import java.util.List;
import java.util.Map;

import com.csManager.csmanager.entity.Invoice;
import com.csManager.csmanager.exception.ClientNotFoundException;
import com.csManager.csmanager.exception.InvoiceNotFoundException;

import org.springframework.http.ResponseEntity;

/**
 * InvoiceService
 */
public interface InvoiceService {
    ResponseEntity<List<Invoice>> getAllInvoices();
    ResponseEntity<Invoice> getInvoiceById(long invoiceId) throws InvoiceNotFoundException;
    ResponseEntity<List<Invoice>> getAllInvoicesByClientId(String clientId) throws ClientNotFoundException;
    ResponseEntity<Invoice> createInvoice(Invoice invoice, String clientId) throws ClientNotFoundException;
    ResponseEntity<Invoice> updateInvoice(long invoiceId, Invoice updatedInvoice) throws InvoiceNotFoundException;
    ResponseEntity<Invoice> partialUpdateInvoice(long invoiceId, Map<Object, Object> fields) throws InvoiceNotFoundException;
    ResponseEntity<?> deletInvoice(long invoiceId) throws InvoiceNotFoundException;;
}
