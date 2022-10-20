package com.csManager.csmanager.controller;

import java.util.List;

import com.csManager.csmanager.entity.Image;
import com.csManager.csmanager.exception.ImageNotFoundException;
import com.csManager.csmanager.service.ImageService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * ImagenController
 */
@RestController
@RequestMapping("/api/v1/images")
@CrossOrigin
public class ImagenController {
    private ImageService imageService;

    public ImagenController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        return imageService.getAllImages();
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<Image> getImageById(@PathVariable long imageId)
        throws ImageNotFoundException {
        return imageService.getImageById(imageId);
    }

    @GetMapping("/vh/{vehicleId}")
    public ResponseEntity<Image> getImageByVehicleId(@PathVariable long vehicleId) {
        return imageService.getImageByVehicleId(vehicleId);
    }

    @PostMapping("/vh/{vehicleId}")
    public ResponseEntity<Image> saveImage(@PathVariable long vehicleId,
            @RequestParam("file") MultipartFile file) throws Exception {
        return imageService.saveImage(vehicleId, file);
    }

    @PutMapping("/{imageId}")
    public ResponseEntity<Image> updateImage(@PathVariable long imageId,
            @RequestParam("file") MultipartFile file) throws Exception {
        return imageService.updateImage(imageId, file);
    }

    @PutMapping("/vh/{vehicleId}")
    public ResponseEntity<Image> updateImageByVehicleId(@PathVariable long vehicleId,
            @RequestParam("file") MultipartFile file) throws Exception {
        return imageService.updateImageByVehicleId(vehicleId, file);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable long imageId)
        throws Exception {
        return imageService.deleteImage(imageId);
    }
}
