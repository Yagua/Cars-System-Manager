package com.friendlyCarsSystem.friendly_cars.repository;

import com.friendlyCarsSystem.friendly_cars.entity.ShoppingCart;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ShoppingCartRepository
 */
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
