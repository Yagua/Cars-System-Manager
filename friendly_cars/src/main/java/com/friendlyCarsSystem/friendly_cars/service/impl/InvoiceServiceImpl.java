package com.friendlyCarsSystem.friendly_cars.service.impl;

import java.util.List;
import java.util.Map;

import com.friendlyCarsSystem.friendly_cars.entity.Invoice;
import com.friendlyCarsSystem.friendly_cars.exception.InvoiceNotFoundException;
import com.friendlyCarsSystem.friendly_cars.repository.InvoiceRepository;
import com.friendlyCarsSystem.friendly_cars.service.InvoiceService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * InvoiceServiceImpl
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Invoice getInvoiceById(String invoiceId)
        throws InvoiceNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Invoice updateInvoice(String invoiceId, Invoice invoice)
        throws InvoiceNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    // @Override
    // public Invoice partialUpdateInvoice(String invoiceId, Map<Object, Object> fields)
    //     throws InvoiceNotFoundException {
    //     // TODO Auto-generated method stub
    //     return null;
    // }

    @Override
    public ResponseEntity<String> deletInvoice(String invoiceId)
        throws InvoiceNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

}
