package com.friendlyCarsSystem.friendly_cars.service.impl;

import java.io.IOException;
import java.util.List;

import com.friendlyCarsSystem.friendly_cars.entity.Image;
import com.friendlyCarsSystem.friendly_cars.exception.ImageNotFoundException;
import com.friendlyCarsSystem.friendly_cars.exception.VehicleNotFoundException;
import com.friendlyCarsSystem.friendly_cars.service.ImageService;
import com.friendlyCarsSystem.friendly_cars.service.VehicleService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * ImageServiceImpl
 */
@Service
public class ImageServiceImpl implements ImageService {
    private VehicleService vehicleService;
    private ImageService imageService;

    public ImageServiceImpl(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
        this.imageService = imageService;
    }

    @Override
    public Image getImageById(long imageId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Image getImageByName(String imageName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Image getImageByVehicleId(long vehicleId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Image> getAllImages() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Image saveImage(MultipartFile file) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Image updateImage(long vehicleId, MultipartFile file) throws IOException, VehicleNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<String> deleteImage(long vehicleId) throws ImageNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }
}
