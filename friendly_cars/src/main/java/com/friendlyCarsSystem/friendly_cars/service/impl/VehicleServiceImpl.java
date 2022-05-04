package com.friendlyCarsSystem.friendly_cars.service.impl;

import java.util.List;
import java.util.Optional;

import com.friendlyCarsSystem.friendly_cars.entity.Vehicle;
import com.friendlyCarsSystem.friendly_cars.repository.VehicleRepository;
import com.friendlyCarsSystem.friendly_cars.service.VehicleService;

import org.springframework.http.ResponseEntity;

/**
 * VehicleServiceImpl
 */
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Vehicle> getVehicleById(String vehicleId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vehicle updateVehicle(String vehicleId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vehicle partialUpdateVehicle(String vehicleId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<String> deletVehicle(String vehicleId) {
        // TODO Auto-generated method stub
        return null;
    }

}
