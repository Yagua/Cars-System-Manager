package com.friendlyCarsSystem.friendly_cars.repository;

import java.util.Optional;

import com.friendlyCarsSystem.friendly_cars.entity.Client;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClientRepository
 */
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByUserNameAndPassword(String userName, String password);
    Optional<Client> findByUserName(String userName);
}
