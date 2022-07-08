package com.csManager.csmanager.repository;

import com.csManager.csmanager.entity.Invoice;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * InvoiceRepository
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
