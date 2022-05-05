package com.friendlyCarsSystem.friendly_cars.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Image
 */
@Entity
@Table(name = "imagen")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageId;

    @Column(name = "nombre_imagen", nullable = false, length = 50)
    private String imageName;

    @Column(name = "tipo_imagen", nullable = false, length = 10)
    private String imageType;

    @Column(name = "imagen", nullable = false)
    private byte[] image = new byte[]{};

    @OneToOne(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST},
        mappedBy = "image"
    )
    private Vehicle vehicle;
}
