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
    Vehicle getVehicleById(long vehicleId) throws VehicleNotFoundException;
    Vehicle createVehicle(Vehicle vehicle);
    Vehicle updateVehicle(long vehicleId, Vehicle updatedVehicle) throws VehicleNotFoundException;;
    Vehicle partialUpdateVehicle(long vehicleId, Map<Object, Object> fields) throws VehicleNotFoundException;;
    ResponseEntity<String> deletVehicle(long vehicleId) throws VehicleNotFoundException;;
}
