package com.friendlyCarsSystem.friendly_cars.repository;

import com.friendlyCarsSystem.friendly_cars.entity.Invoice;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * InvoiceRepository
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
