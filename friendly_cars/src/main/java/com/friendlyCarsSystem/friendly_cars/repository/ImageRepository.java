package com.friendlyCarsSystem.friendly_cars.repository;

import java.util.Optional;

import com.friendlyCarsSystem.friendly_cars.entity.Image;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ImageRepository
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByImageName(String imageName);
}
