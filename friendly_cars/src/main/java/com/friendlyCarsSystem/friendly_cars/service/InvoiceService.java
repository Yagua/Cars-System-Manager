package com.friendlyCarsSystem.friendly_cars.service;

import java.util.List;
import java.util.Map;

import com.friendlyCarsSystem.friendly_cars.entity.Invoice;
import com.friendlyCarsSystem.friendly_cars.exception.ClientNotFoundException;
import com.friendlyCarsSystem.friendly_cars.exception.InvoiceNotFoundException;
import com.friendlyCarsSystem.friendly_cars.exception.ShoppingCartNotFoundException;

import org.springframework.http.ResponseEntity;

/**
 * InvoiceService
 */
public interface InvoiceService {
    List<Invoice> getAllInvoices();
    Invoice getInvoiceById(long invoiceId) throws InvoiceNotFoundException;
    List<Invoice> getAllInvoicesByShoppingCartId (long shoppingCartId) throws ClientNotFoundException;
    Invoice createInvoice(Invoice invoice, long ShoppingCartId) throws ShoppingCartNotFoundException;
    Invoice updateInvoice(long invoiceId, Invoice updatedInvoice) throws InvoiceNotFoundException;
    Invoice partialUpdateInvoice(long invoiceId, Map<Object, Object> fields) throws InvoiceNotFoundException;
    ResponseEntity<String> deletInvoice(long invoiceId) throws InvoiceNotFoundException;;
}
