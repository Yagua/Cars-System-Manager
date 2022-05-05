package com.friendlyCarsSystem.friendly_cars.service;

import java.util.List;
import java.util.Map;

import com.friendlyCarsSystem.friendly_cars.entity.Vehicle;
import com.friendlyCarsSystem.friendly_cars.exception.VehicleNotFoundException;

import org.springframework.http.ResponseEntity;

/**
 * VehicleService
 */
public interface VehicleService {
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(String vehicleId) throws VehicleNotFoundException;
    Vehicle createVehicle(Vehicle vehicle);
    Vehicle updateVehicle(String vehicleId, Vehicle vehicle) throws VehicleNotFoundException;;
    Vehicle partialUpdateVehicle(String vehicleId, Map<Object, Object> fields) throws VehicleNotFoundException;;
    ResponseEntity<String> deletVehicle(String vehicleId) throws VehicleNotFoundException;;
}
