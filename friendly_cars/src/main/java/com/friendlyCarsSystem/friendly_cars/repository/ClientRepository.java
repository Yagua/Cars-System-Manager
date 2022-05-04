package com.friendlyCarsSystem.friendly_cars.repository;

import com.friendlyCarsSystem.friendly_cars.entity.Client;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClientRepository
 */
public interface ClientRepository extends JpaRepository<Client, String> {

}
