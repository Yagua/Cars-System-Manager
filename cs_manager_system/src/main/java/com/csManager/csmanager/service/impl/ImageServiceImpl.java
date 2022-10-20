package com.csManager.csmanager.service.impl;

import java.util.List;

import com.csManager.csmanager.entity.Image;
import com.csManager.csmanager.entity.Vehicle;
import com.csManager.csmanager.exception.ImageNotFoundException;
import com.csManager.csmanager.exception.VehicleNotFoundException;
import com.csManager.csmanager.repository.ImageRepository;
import com.csManager.csmanager.repository.VehicleRepository;
import com.csManager.csmanager.service.ImageService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * ImageServiceImpl
 */
@Service
public class ImageServiceImpl implements ImageService {
    private ImageRepository imageRepository;
    private VehicleRepository vehicleRepository;

    public ImageServiceImpl(ImageRepository imageRepository,
            VehicleRepository vehicleRepository) {
        this.imageRepository = imageRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public ResponseEntity<Image> getImageById(long imageId) throws ImageNotFoundException {
        Image image = imageRepository.findById(imageId)
            .orElseThrow(() -> new ImageNotFoundException(
                        String.format("Image identified with '%d' not found",
                            imageId)));
        return ResponseEntity.ok(image);
    }

    @Override
    public ResponseEntity<Image> getImageByName(String imageName) throws ImageNotFoundException {
        Image image = imageRepository.findByImageName(imageName)
            .orElseThrow(() -> new ImageNotFoundException(
                        String.format("Image'%d' not found",
                            imageName)));

        return ResponseEntity.ok(image);
    }

    @Override
    public ResponseEntity<Image> getImageByVehicleId(long vehicleId) throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new ImageNotFoundException(
                        String.format("Vehicle identified with '%d' not found",
                            vehicleId)));
        Image vehicleImage = vehicle.getImage();
        return ResponseEntity.ok(vehicleImage);
    }

    @Override
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageRepository.findAll();
        return ResponseEntity.ok(images);
    }

    @Override
    public ResponseEntity<Image> saveImage(long vehicleId, MultipartFile file) throws Exception {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new ImageNotFoundException(
                        String.format("Vehicle identified with '%d' not found",
                            vehicleId)));

        if(vehicle.getImage() != null)
            throw new Exception(String.format(
                        "the vehicle '%d' alraedy has an image", vehicleId));

        Image image = prepareImage(file);
        vehicle.setImage(image);
        vehicleRepository.save(vehicle);

        Image vehicleImage = vehicle.getImage();
        return ResponseEntity.ok(vehicleImage);
    }

    @Override
    public ResponseEntity<Image> updateImage(long imageId, MultipartFile file) throws Exception {
        Image image = imageRepository.findById(imageId)
            .orElseThrow(() -> new ImageNotFoundException(
                        String.format("Image identified with '%d' not found",
                            imageId)));
        Image newImage = prepareImage(file);

        // image.setVehicle(newImage.getVehicle());
        // image.setImageId(newImage.getImageId());
        image.setImageName(newImage.getImageName());
        image.setImageContent(newImage.getImageContent());
        image.setImageType(newImage.getImageType());

        Image savedImage = imageRepository.save(image);
        return ResponseEntity.ok(savedImage);
    }

    @Override
    public ResponseEntity<Image> updateImageByVehicleId(long vehicleId, MultipartFile file)
        throws Exception {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new VehicleNotFoundException(
                        String.format("Vehicle Identified with '%s' not found",
                            vehicleId)));

        Image vehicleImg = vehicle.getImage();
        if(vehicleImg != null) {
            return updateImage(vehicleImg.getImageId(), file);
        }

        Image newVehicleImg = prepareImage(file);
        vehicle.setImage(newVehicleImg);
        vehicleRepository.save(vehicle);
        Image vehicleImage = vehicle.getImage();

        return ResponseEntity.ok(vehicleImage);
    }

    @Override
    public ResponseEntity<?> deleteImage(long imageId) throws Exception {
        Image image = imageRepository.findById(imageId)
            .orElseThrow(() -> new ImageNotFoundException(
                        String.format("Image identified with '%d' not found",
                            imageId)));

        Vehicle vehicle = image.getVehicle();
        vehicle.setImage(null);

        imageRepository.delete(image);
        return ResponseEntity.noContent().build();
    }

    public Image prepareImage(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new Exception(String.format(
                            "Filename contains invalid path sequence %s",
                            fileName));
            }

            Image image = Image.builder()
                .imageName(fileName)
                .imageType(file.getContentType())
                .imageContent(file.getBytes())
                .build();

            return image;
        } catch(Exception e) {
            throw new Exception(String.format("Could not save file '%s'",
                        fileName));
        }
    }
}
