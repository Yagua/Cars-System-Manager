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
