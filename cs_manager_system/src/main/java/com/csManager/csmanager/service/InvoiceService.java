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
    List<Invoice> getAllInvoices();
    Invoice getInvoiceById(long invoiceId) throws InvoiceNotFoundException;
    List<Invoice> getAllInvoicesByClientId(String clientId) throws ClientNotFoundException;
    Invoice createInvoice(Invoice invoice, String clientId) throws ClientNotFoundException;
    Invoice updateInvoice(long invoiceId, Invoice updatedInvoice) throws InvoiceNotFoundException;
    Invoice partialUpdateInvoice(long invoiceId, Map<Object, Object> fields) throws InvoiceNotFoundException;
    ResponseEntity<String> deletInvoice(long invoiceId) throws InvoiceNotFoundException;;
}
