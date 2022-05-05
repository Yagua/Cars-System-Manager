package com.friendlyCarsSystem.friendly_cars.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.friendlyCarsSystem.friendly_cars.entity.Invoice;
import com.friendlyCarsSystem.friendly_cars.entity.Vehicle;
import com.friendlyCarsSystem.friendly_cars.exception.InvoiceNotFoundException;
import com.friendlyCarsSystem.friendly_cars.exception.VehicleNotFoundException;
import com.friendlyCarsSystem.friendly_cars.repository.InvoiceRepository;
import com.friendlyCarsSystem.friendly_cars.repository.VehicleRepository;
import com.friendlyCarsSystem.friendly_cars.service.VehicleService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

/**
 * VehicleServiceImpl
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;
    private InvoiceRepository invoiceRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository,
            InvoiceRepository invoiceRepository) {
        this.vehicleRepository = vehicleRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleById(long vehicleId)
        throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new VehicleNotFoundException(
                        String.format("Vehicle Identified with '%s' not found",
                            vehicleId)));
        return vehicle;
    }

    @Override
    public List<Vehicle> getAllVehiclesByInvoiceId(long invoiceId)
        throws InvoiceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceId)
            .orElseThrow(() -> new InvoiceNotFoundException(
                        String.format("Invoice identified with '%s' not found",
                            invoiceId)));
        return invoice.getVehicles();
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle, long invoiceId)
        throws InvoiceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceId)
            .orElseThrow(() -> new InvoiceNotFoundException(
                        String.format("Invoice identified with '%s' not found",
                            invoiceId)));
        List<Vehicle> vehicles = invoice.getVehicles();
        vehicles.add(vehicle);
        vehicle.setInvoice(invoice);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(long vehicleId, Vehicle updatedVehicle)
        throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new VehicleNotFoundException(
                        String.format("Vehicle Identified with '%s' not found",
                            vehicleId)));

        // vehicle.setInvoice(updatedVehicle.getInvoice());
        vehicle.setModel(updatedVehicle.getModel());
        vehicle.setAvailable(updatedVehicle.isAvailable());
        vehicle.setSellerName(updatedVehicle.getSellerName());
        vehicle.setDeliveryDate(updatedVehicle.getDeliveryDate());
        vehicle.setNumberOfDoors(updatedVehicle.getNumberOfDoors());
        vehicle.setManufacturer(updatedVehicle.getManufacturer());
        vehicle.setVehicleWeight(updatedVehicle.getVehicleWeight());
        vehicle.setPlaceOfManufacture(updatedVehicle.getPlaceOfManufacture());

        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle partialUpdateVehicle(long vehicleId, Map<Object, Object> fields)
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

        return vehicleRepository.save(vehicle);
    }

    @Override
    public ResponseEntity<String> deletVehicle(long vehicleId)
        throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new VehicleNotFoundException(
                        String.format("Vehicle Identified with '%s' not found",
                            vehicleId)));
        vehicleRepository.delete(vehicle);
        return ResponseEntity.ok(String.format("Vehicle '%d' deleted", vehicleId));
    }
}
