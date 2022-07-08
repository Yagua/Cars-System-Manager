package com.csManager.csmanager.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Invoice
 */
@Entity
@Table(name = "factura")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private long invoiceId;

    @Column(name = "precio_venta", nullable =  false)
    private double totalPrice;

    @Column(name = "costos_adicionales", nullable =  true)
    private double aditionalPrices = 0D;

    @Column(name = "descuento_sobre_total", nullable = true)
    private double discount = 0D;

    @ElementCollection
    private List<Vehicle> vehicles = new ArrayList<>();

    @JsonBackReference
    @ManyToOne(
        cascade = {CascadeType.MERGE, CascadeType.PERSIST},
        fetch = FetchType.LAZY,
        optional = false
    )
    @JoinColumn(name = "id_cliente")
    private Client client;

    @Column(
        name = "fecha_venta",
        nullable = true,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfSale;

    @Column(
        name = "fecha_creacion",
        nullable = true,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date timeStamp;
}
