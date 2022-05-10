package com.friendlyCarsSystem.friendly_cars.controller;

import java.util.List;

import com.friendlyCarsSystem.friendly_cars.entity.ShoppingCart;
import com.friendlyCarsSystem.friendly_cars.exception.ClientNotFoundException;
import com.friendlyCarsSystem.friendly_cars.exception.ShoppingCartNotFoundException;
import com.friendlyCarsSystem.friendly_cars.service.ShoppingCartService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ShoppingCartController
 */
@RestController
@RequestMapping("/api/v1/carts")
@CrossOrigin
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartService.getAllShoppingCarts();
    }

    @GetMapping("/{shoppingCartId}")
    public ShoppingCart getShoppingCartById(@PathVariable long shoppingCartId)
        throws ShoppingCartNotFoundException {
        return shoppingCartService.getShoppingCartById(shoppingCartId);
    }

    @GetMapping("/c/{clientId}")
    public ShoppingCart getShoppingCartByClientId(@PathVariable String clientId)
        throws ClientNotFoundException {
        return shoppingCartService.getShoppingCartClientId(clientId);
    }

    @PostMapping("/c/{clientId}")
    public ShoppingCart createShoppingCart(@PathVariable String clientId,
            @RequestBody ShoppingCart shoppingCart) {
        return shoppingCartService.createShoppingCart(clientId, shoppingCart);
    }

    @PostMapping("/{shoppingCartId}/vh/{vehicleId}")
    public ShoppingCart dropVehicleToShoppingCart(@PathVariable long shoppingCartId,
            @PathVariable long vehicleId) throws Exception {
        return shoppingCartService.dropVehicleOfShoppingCart(shoppingCartId, vehicleId);
    }

    @DeleteMapping("/{shoppingCartId}")
    public ResponseEntity<String> deleteShoppingCart(@PathVariable long shoppingCartId)
        throws ShoppingCartNotFoundException {
        return shoppingCartService.deleteShoppingCart(shoppingCartId);
    }
}
