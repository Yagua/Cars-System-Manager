package com.friendlyCarsSystem.friendly_cars.controller;

import java.util.List;

import com.friendlyCarsSystem.friendly_cars.entity.Image;
import com.friendlyCarsSystem.friendly_cars.exception.ImageNotFoundException;
import com.friendlyCarsSystem.friendly_cars.service.ImageService;

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
    public List<Image> getAllImages() {
        return imageService.getAllImages();
    }

    @GetMapping("/{imageId}")
    public Image getImageById(@PathVariable long imageId)
        throws ImageNotFoundException {
        return imageService.getImageById(imageId);
    }

    @GetMapping("/vh/{vehicleId}")
    public Image getImageByVehicleId(@PathVariable long vehicleId) {
        return imageService.getImageByVehicleId(vehicleId);
    }

    @PostMapping("/vh/{vehicleId}")
    public Image saveImage(@PathVariable long vehicleId,
            @RequestParam("file") MultipartFile file) throws Exception {
        return imageService.saveImage(vehicleId, file);
    }

    @PutMapping("/{imageId}")
    public Image updateImage(@PathVariable long imageId,
            @RequestParam("file") MultipartFile file) throws Exception {
        return imageService.updateImage(imageId, file);
    }

    @PutMapping("/vh/{vehicleId}")
    public Image updateImageByVehicleId(@PathVariable long vehicleId,
            @RequestParam("file") MultipartFile file) throws Exception {
        return imageService.updateImageByVehicleId(vehicleId, file);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<String> deleteImage(@PathVariable long imageId)
        throws Exception {
        return imageService.deleteImage(imageId);
    }
}
