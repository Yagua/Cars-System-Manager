package com.friendlyCarsSystem.friendly_cars.service;

import java.io.IOException;
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
    Image getImageById(long imageId);
    Image getImageByName(String imageName);
    Image getImageByVehicleId(long vehicleId);
    List<Image> getAllImages();
    Image saveImage(MultipartFile file) throws IOException;
    Image updateImage(long vehicleId, MultipartFile file) throws IOException, VehicleNotFoundException;
    ResponseEntity<String> deleteImage(long vehicleId) throws ImageNotFoundException;
}
