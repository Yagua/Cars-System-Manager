package com.csManager.csmanager.service;

import java.util.List;

import com.csManager.csmanager.entity.Image;
import com.csManager.csmanager.exception.ImageNotFoundException;
import com.csManager.csmanager.exception.VehicleNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * ImageService
 */
public interface ImageService {
    ResponseEntity<Image> getImageById(long imageId) throws ImageNotFoundException;
    ResponseEntity<Image> getImageByName(String imageName) throws ImageNotFoundException;
    ResponseEntity<Image> getImageByVehicleId(long vehicleId) throws VehicleNotFoundException;
    ResponseEntity<List<Image>> getAllImages();
    ResponseEntity<Image> saveImage(long vehicleId, MultipartFile file) throws Exception;
    ResponseEntity<Image> updateImage(long imageId, MultipartFile file) throws Exception;
    ResponseEntity<Image> updateImageByVehicleId(long vehicleId, MultipartFile file) throws Exception;
    ResponseEntity<?> deleteImage(long imageId) throws Exception;
}
