package com.friendlyCarsSystem.friendly_cars.service.impl;

import java.util.List;
import java.util.Map;

import com.friendlyCarsSystem.friendly_cars.entity.Vehicle;
import com.friendlyCarsSystem.friendly_cars.exception.VehicleNotFoundException;
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
    public Vehicle getVehicleById(long vehicleId)
        throws VehicleNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vehicle updateVehicle(long vehicleId, Vehicle vehicle)
        throws VehicleNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vehicle partialUpdateVehicle(long vehicleId, Map<Object, Object> fields)
        throws VehicleNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<String> deletVehicle(long vehicleId)
        throws VehicleNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }
}
