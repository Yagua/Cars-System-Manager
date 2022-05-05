package com.friendlyCarsSystem.friendly_cars.controller;

import com.friendlyCarsSystem.friendly_cars.service.ImageService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ImagenController
 */
@RestController
@RequestMapping("/api/v1/images")
public class ImagenController {
    private ImageService imageService;
}
