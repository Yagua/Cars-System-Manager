package com.friendlyCarsSystem.friendly_cars.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClientController
 */
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @GetMapping
    public String foobar() {
        return "foo bar";
    }
}
