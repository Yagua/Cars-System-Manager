package com.friendlyCarsSystem.friendly_cars.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Vehicle
 */
@Entity
@Table(name = "vehiculo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private long vehicleId;

    @Column(name = "modelo", nullable = false, length = 30)
    private String model;

    @Column(name = "peso_vehiculo", nullable = false)
    private float vehicleWeight;

    @Column(name = "disponible", nullable = true)
    private boolean available = true;

    @Column(name = "lugar_fabricacion", nullable = false, length = 40)
    private String placeOfManufacture;

    @Column(name = "numero_puertas", nullable = false)
    private int numberOfDoors;

    @Column(
        name = "fecha_entrega",
        nullable = true,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;

    @Column(
        name = "fecha_creacion",
        nullable = true,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date timeStamp;

    @Column(name = "fabricante", nullable = false, length = 40)
    private String manufacturer;

    @Column(name = "vendedor", nullable = false, length = 40)
    private String sellerName;

    @JsonBackReference
    @ManyToOne(
        cascade = {CascadeType.PERSIST, CascadeType.MERGE},
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "id_factura", nullable = false)
    private Invoice invoice;

    @OneToOne(
        orphanRemoval = true,
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "id_imagen", nullable = true)
    private Image image;
}
