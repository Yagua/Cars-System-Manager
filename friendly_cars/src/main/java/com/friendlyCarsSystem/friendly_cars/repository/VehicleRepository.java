package com.friendlyCarsSystem.friendly_cars.repository;

import com.friendlyCarsSystem.friendly_cars.entity.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * VehicleRepository
 */
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
