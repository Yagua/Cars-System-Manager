package com.friendlyCarsSystem.friendly_cars.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Client
 */

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

    @Id
    @Column(name = "cliente_id", length = 10)
    private String clientId;

    @Column(name = "primer_nombre", nullable =  false, length = 20)
    private String firstName;

    @Column(name = "segundo_nombre", nullable = true, length = 20)
    private String secondName;

    @Column(name = "apellido_paterno", nullable = false, length = 20)
    private String paternalLastName;

    @Column(name = "apellido_materno", nullable = true, length = 20)
    private String maternalLastName;

    @Column(name = "direccion", nullable = false, length = 70)
    private String address;

    @Column(name = "telefono", nullable = false, length = 15)
    private String telephoneNumber;

    @Column(
        name = "nombre_usuario",
        nullable = false, length = 20,
        unique =  true
    )
    private String userName;

    @Column(name = "contrasena_usuario", nullable = false, length = 50)
    private String password;

    // @OneToMany(
    //     fetch = FetchType.LAZY,
    //     cascade = CascadeType.ALL,
    //     orphanRemoval = true,
    //     mappedBy = "client"
    // )
    // private List<Invoice> invoices = new ArrayList<>();

    @OneToOne(
        fetch = FetchType.EAGER,
        orphanRemoval = true,
        cascade = CascadeType.ALL
    )
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart = new ShoppingCart();

    @Column(
        name = "fecha_creacion",
        nullable = true,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    private Date timeStamp;
}
