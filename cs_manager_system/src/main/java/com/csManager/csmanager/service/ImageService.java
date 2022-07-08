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
    Image getImageById(long imageId) throws ImageNotFoundException;
    Image getImageByName(String imageName) throws ImageNotFoundException;
    Image getImageByVehicleId(long vehicleId) throws VehicleNotFoundException;
    List<Image> getAllImages();
    Image saveImage(long vehicleId, MultipartFile file) throws Exception;
    Image updateImage(long imageId, MultipartFile file) throws Exception;
    Image updateImageByVehicleId(long vehicleId, MultipartFile file) throws Exception;
    ResponseEntity<String> deleteImage(long imageId) throws Exception;
}
