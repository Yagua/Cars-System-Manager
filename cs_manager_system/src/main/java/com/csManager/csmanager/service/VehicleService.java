package com.csManager.csmanager.service;

import java.util.List;
import java.util.Map;

import com.csManager.csmanager.entity.Vehicle;
import com.csManager.csmanager.exception.ShoppingCartNotFoundException;
import com.csManager.csmanager.exception.VehicleNotFoundException;

import org.springframework.http.ResponseEntity;

/**
 * VehicleService
 */
public interface VehicleService {
    ResponseEntity<List<Vehicle>> getAllVehicles();
    ResponseEntity<List<Vehicle>> getAllVehiclesByShoppingCartId(long shoppingCartId) throws ShoppingCartNotFoundException;
    ResponseEntity<Vehicle> getVehicleById(long vehicleId) throws VehicleNotFoundException;
    ResponseEntity<Vehicle> createVehicle(Vehicle vehicle);
    ResponseEntity<Vehicle> updateVehicle(long vehicleId, Vehicle updatedVehicle) throws VehicleNotFoundException;;
    ResponseEntity<Vehicle> partialUpdateVehicle(long vehicleId, Map<Object, Object> fields) throws VehicleNotFoundException;;
    ResponseEntity<?> deleteVehicle(long vehicleId) throws VehicleNotFoundException;;
    ResponseEntity<Vehicle> addVehicleToShoppingCart(long shoppingCartId, long vehicleId) throws Exception;
}
