package com.csManager.csmanager.repository;

import java.util.Optional;

import com.csManager.csmanager.entity.Image;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ImageRepository
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByImageName(String imageName);
}
