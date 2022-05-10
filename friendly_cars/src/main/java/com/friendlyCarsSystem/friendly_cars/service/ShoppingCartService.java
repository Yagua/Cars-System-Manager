package com.friendlyCarsSystem.friendly_cars.service;

import java.util.List;
import java.util.Map;

import com.friendlyCarsSystem.friendly_cars.entity.ShoppingCart;
import com.friendlyCarsSystem.friendly_cars.exception.ClientNotFoundException;
import com.friendlyCarsSystem.friendly_cars.exception.ShoppingCartNotFoundException;

import org.springframework.http.ResponseEntity;

/**
 * ShoppingCartService
 */
public interface ShoppingCartService {
    List<ShoppingCart> getAllShoppingCarts();
    ShoppingCart getShoppingCartById(long shoppingCartId)
            throws ShoppingCartNotFoundException;
    ShoppingCart getShoppingCartClientId(String clientId) throws ClientNotFoundException;
    ShoppingCart updateShoppingCart(long shoppingCartId, ShoppingCart updatedShoppingCart)
            throws ShoppingCartNotFoundException;
    ShoppingCart partialUpdateShoppingCart(long shoppingCartId, Map<Object, Object> fields)
        throws ShoppingCartNotFoundException;
    ResponseEntity<String> deleteShoppingCart(long shoppingCartId)
            throws ShoppingCartNotFoundException;
    ShoppingCart createShoppingCart(String clientId, ShoppingCart shoppingCart) throws ClientNotFoundException;
    ShoppingCart dropVehicleOfShoppingCart(long shoppingCartId, long vehicleId) throws Exception;
}
