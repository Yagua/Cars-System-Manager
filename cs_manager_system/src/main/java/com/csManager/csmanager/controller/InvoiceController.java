package com.csManager.csmanager.controller;

import java.util.List;
import java.util.Map;

import com.csManager.csmanager.entity.Invoice;
import com.csManager.csmanager.exception.ClientNotFoundException;
import com.csManager.csmanager.exception.InvoiceNotFoundException;
import com.csManager.csmanager.service.InvoiceService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * InvoiceController
 */
@RestController
@RequestMapping("/api/v1/invoices")
@CrossOrigin
public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/{invoiceId}")
    public Invoice getInvoiceById(@PathVariable long invoiceId)
        throws InvoiceNotFoundException {
        return invoiceService.getInvoiceById(invoiceId);
    }

    @GetMapping("/c/{clientId}")
    public List<Invoice> getAllInvoicesByClientId(@PathVariable String clientId)
        throws ClientNotFoundException {
        return invoiceService.getAllInvoicesByClientId(clientId);
    }

    @PostMapping("/c/{clientId}")
    public Invoice createInovice(@RequestBody Invoice invoice,
            @PathVariable String clientId) {
        return invoiceService.createInvoice(invoice, clientId);
    }

    @PutMapping("/{invoiceId}")
    public Invoice updateInvoice(@RequestBody Invoice updatedInvoice,
            @PathVariable long invoiceId) throws InvoiceNotFoundException {
        return invoiceService.updateInvoice(invoiceId, updatedInvoice);
    }

    @PatchMapping("/{invoiceId}")
    public Invoice partialUpdateInvoice(@RequestBody Map<Object, Object> fields,
            @PathVariable long invoiceId) throws InvoiceNotFoundException {
        return invoiceService.partialUpdateInvoice(invoiceId, fields);
    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<String> deleteInvoice(@PathVariable long invoiceId)
        throws InvoiceNotFoundException {
        return invoiceService.deletInvoice(invoiceId);
    }
}
