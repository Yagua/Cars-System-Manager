package com.csManager.csmanager.repository;

import com.csManager.csmanager.entity.ShoppingCart;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ShoppingCartRepository
 */
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
