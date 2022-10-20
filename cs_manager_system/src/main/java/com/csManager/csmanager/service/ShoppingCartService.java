package com.csManager.csmanager.service;

import java.util.List;
import java.util.Map;

import com.csManager.csmanager.entity.ShoppingCart;
import com.csManager.csmanager.exception.ClientNotFoundException;
import com.csManager.csmanager.exception.ShoppingCartNotFoundException;

import org.springframework.http.ResponseEntity;

/**
 * ShoppingCartService
 */
public interface ShoppingCartService {
    ResponseEntity<List<ShoppingCart>> getAllShoppingCarts();
    ResponseEntity<ShoppingCart> getShoppingCartById(long shoppingCartId)
            throws ShoppingCartNotFoundException;
    ResponseEntity<ShoppingCart> getShoppingCartClientId(String clientId)
            throws ClientNotFoundException;
    ResponseEntity<ShoppingCart> updateShoppingCart(long shoppingCartId, ShoppingCart updatedShoppingCart)
            throws ShoppingCartNotFoundException;
    ResponseEntity<ShoppingCart> partialUpdateShoppingCart(long shoppingCartId, Map<Object, Object> fields)
            throws ShoppingCartNotFoundException;
    ResponseEntity<?> deleteShoppingCart(long shoppingCartId)
            throws ShoppingCartNotFoundException;
    ResponseEntity<ShoppingCart> createShoppingCart(String clientId, ShoppingCart shoppingCart)
            throws ClientNotFoundException;
    ResponseEntity<ShoppingCart> dropVehicleOfShoppingCart(long shoppingCartId, long vehicleId)
            throws Exception;
}
