package com.csManager.csmanager.controller;

import java.util.List;
import java.util.Map;

import com.csManager.csmanager.entity.Vehicle;
import com.csManager.csmanager.exception.VehicleNotFoundException;
import com.csManager.csmanager.service.VehicleService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * VehicleController
 */
@RestController
@RequestMapping("/api/v1/vehicles")
@CrossOrigin
public class VehicleController {
    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/sc/{shoppingCartId}")
    public List<Vehicle> getAllVehiclesByInvoiceId(@PathVariable long shoppingCartId) {
        return vehicleService.getAllVehiclesByShoppingCartId(shoppingCartId);
    }

    @GetMapping("/{vehicleId}")
    public Vehicle getVehicleById(@PathVariable long vehicleId)
        throws VehicleNotFoundException {
        return vehicleService.getVehicleById(vehicleId);
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    @PutMapping("/{vehicleId}")
    public Vehicle updateVehicle(@PathVariable long vehicleId,
            @RequestBody Vehicle updatedVehicle) throws VehicleNotFoundException {
        return vehicleService.updateVehicle(vehicleId, updatedVehicle);
    }

    @PatchMapping("/{vehicleId}")
    public Vehicle partialUpdateVehicle(@PathVariable long vehicleId,
            @RequestBody Map<Object, Object> fields) throws VehicleNotFoundException {
        return vehicleService.partialUpdateVehicle(vehicleId, fields);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(@PathVariable long vehicleId)
        throws VehicleNotFoundException {
        return vehicleService.deleteVehicle(vehicleId);
    }

    @PostMapping("/{vehicleId}/sc/{shoppingCartId}")
    public Vehicle addVehicleToShoppingCartId(@PathVariable long vehicleId,
            @PathVariable long shoppingCartId) throws Exception {
        return vehicleService.addVehicleToShoppingCart(shoppingCartId, vehicleId);
    }
}
