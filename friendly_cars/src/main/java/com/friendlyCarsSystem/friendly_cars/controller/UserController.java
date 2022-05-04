package com.friendlyCarsSystem.friendly_cars.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping
    public String sayHello() {
        return "hello world";
    }
}
