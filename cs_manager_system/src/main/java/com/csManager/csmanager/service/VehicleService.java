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
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAllVehiclesByShoppingCartId(long shoppingCartId) throws ShoppingCartNotFoundException;
    Vehicle getVehicleById(long vehicleId) throws VehicleNotFoundException;
    Vehicle createVehicle(Vehicle vehicle);
    Vehicle updateVehicle(long vehicleId, Vehicle updatedVehicle) throws VehicleNotFoundException;;
    Vehicle partialUpdateVehicle(long vehicleId, Map<Object, Object> fields) throws VehicleNotFoundException;;
    ResponseEntity<String> deleteVehicle(long vehicleId) throws VehicleNotFoundException;;
    Vehicle addVehicleToShoppingCart(long shoppingCartId, long vehicleId) throws Exception;
}
