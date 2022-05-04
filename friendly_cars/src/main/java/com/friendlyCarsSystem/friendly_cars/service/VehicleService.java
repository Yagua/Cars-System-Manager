package com.friendlyCarsSystem.friendly_cars.service;

import java.util.List;
import java.util.Optional;

import com.friendlyCarsSystem.friendly_cars.entity.Vehicle;

import org.springframework.http.ResponseEntity;

/**
 * VehicleService
 */
public interface VehicleService {
    List<Vehicle> getAllVehicles();
    Optional<Vehicle> getVehicleById(String vehicleId);
    Vehicle createVehicle(Vehicle vehicle);
    Vehicle updateVehicle(String vehicleId);
    Vehicle partialUpdateVehicle(String vehicleId);
    ResponseEntity<String> deletVehicle(String vehicleId);
}
