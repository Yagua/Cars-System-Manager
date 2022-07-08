package com.csManager.csmanager.controller;

import com.csManager.csmanager.entity.Client;
import com.csManager.csmanager.exception.ClientNotFoundException;
import com.csManager.csmanager.payload.ClientDTO;
import com.csManager.csmanager.service.ClientService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController
 */
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {
    private ClientService clientService;

    public AuthController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/login")
    public Client loginClient(@RequestBody ClientDTO loginDTO)
        throws ClientNotFoundException {
        return clientService.loginClient(loginDTO.getUserName(),
                loginDTO.getPassword());
    }

    @PatchMapping("/update-password")
    public Client changePassword(@RequestBody ClientDTO client)
        throws ClientNotFoundException {
        return clientService.changePassword( client.getUserName(),
                client.getPassword());
    }
}
