package com.friendlyCarsSystem.friendly_cars.service;

import java.util.List;

import com.friendlyCarsSystem.friendly_cars.entity.Image;
import com.friendlyCarsSystem.friendly_cars.exception.ImageNotFoundException;
import com.friendlyCarsSystem.friendly_cars.exception.VehicleNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * ImageService
 */
public interface ImageService {
    Image getImageById(long imageId) throws ImageNotFoundException;
    Image getImageByName(String imageName) throws ImageNotFoundException;
    Image getImageByVehicleId(long vehicleId) throws VehicleNotFoundException;
    List<Image> getAllImages();
    Image saveImage(long vehicleId, MultipartFile file) throws Exception;
    Image updateImage(long imageId, MultipartFile file) throws Exception;
    ResponseEntity<String> deleteImage(long imageId) throws Exception;
}
