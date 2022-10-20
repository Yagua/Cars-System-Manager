package com.csManager.csmanager.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.csManager.csmanager.entity.Image;
import com.csManager.csmanager.entity.ShoppingCart;
import com.csManager.csmanager.entity.Vehicle;
import com.csManager.csmanager.exception.ShoppingCartNotFoundException;
import com.csManager.csmanager.exception.VehicleNotFoundException;
import com.csManager.csmanager.repository.ShoppingCartRepository;
import com.csManager.csmanager.repository.VehicleRepository;
import com.csManager.csmanager.service.VehicleService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

/**
 * VehicleServiceImpl
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;
    private ShoppingCartRepository shoppingCartRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository,
            ShoppingCartRepository shoppingCartRepository) {
        this.vehicleRepository = vehicleRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return ResponseEntity.ok(vehicles);
    }

    @Override
    public ResponseEntity<Vehicle> getVehicleById(long vehicleId)
        throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new VehicleNotFoundException(
                        String.format("Vehicle Identified with '%s' not found",
                            vehicleId)));
        return ResponseEntity.ok(vehicle);
    }

    @Override
    public ResponseEntity<List<Vehicle>> getAllVehiclesByShoppingCartId(long shoppingCartId)
        throws ShoppingCartNotFoundException {
        ShoppingCart cart = shoppingCartRepository.findById(shoppingCartId)
            .orElseThrow(() -> new ShoppingCartNotFoundException(
                        String.format("Shopping Cart identified with '%d' not found",
                            shoppingCartId)));
        List<Vehicle> vehicles = cart.getVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @Override
    public ResponseEntity<Vehicle> createVehicle(Vehicle vehicle) {
        Image image = vehicle.getImage();
        if(image != null) image.setVehicle(vehicle);
        Vehicle newVehicle = vehicleRepository.save(vehicle);
        return ResponseEntity.ok(newVehicle);
    }

    @Override
    public ResponseEntity<Vehicle> updateVehicle(long vehicleId, Vehicle updatedVehicle)
        throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new VehicleNotFoundException(
                        String.format("Vehicle Identified with '%s' not found",
                            vehicleId)));

        // vehicle.setInvoice(updatedVehicle.getInvoice());
        ShoppingCart cart = updatedVehicle.getShoppingCart();
        if(cart != null) vehicle.setShoppingCart(cart);
        vehicle.setModel(updatedVehicle.getModel());
        vehicle.setAvailable(updatedVehicle.isAvailable());
        vehicle.setSellerName(updatedVehicle.getSellerName());
        vehicle.setDeliveryDate(updatedVehicle.getDeliveryDate());
        vehicle.setNumberOfDoors(updatedVehicle.getNumberOfDoors());
        vehicle.setManufacturer(updatedVehicle.getManufacturer());
        vehicle.setVehicleWeight(updatedVehicle.getVehicleWeight());
        vehicle.setPlaceOfManufacture(updatedVehicle.getPlaceOfManufacture());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return ResponseEntity.ok(savedVehicle);
    }

    @Override
    public ResponseEntity<Vehicle> partialUpdateVehicle(long vehicleId, Map<Object, Object> fields)
        throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new VehicleNotFoundException(
                        String.format("Vehicle Identified with '%s' not found",
                            vehicleId)));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Vehicle.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, vehicle, value);
        });

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);

        return ResponseEntity.ok(updatedVehicle);
    }

    @Override
    public ResponseEntity<?> deleteVehicle(long vehicleId)
        throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new VehicleNotFoundException(
                        String.format("Vehicle Identified with '%s' not found",
                            vehicleId)));
        vehicleRepository.delete(vehicle);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Vehicle> addVehicleToShoppingCart(long shoppingCartId,
            long vehicleId) throws Exception {
        ShoppingCart cart = shoppingCartRepository.findById(shoppingCartId)
            .orElseThrow(() -> new ShoppingCartNotFoundException(
                        String.format("Shopping Cart identified with '%d' not found",
                            shoppingCartId)));
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new VehicleNotFoundException(
                        String.format("Vehicle Identified with '%s' not found",
                            vehicleId)));
        vehicle.setShoppingCart(cart);
        cart.getVehicles().add(vehicle);
        Vehicle newVehicle =  vehicleRepository.save(vehicle);
        return ResponseEntity.ok(newVehicle);
    }
}
