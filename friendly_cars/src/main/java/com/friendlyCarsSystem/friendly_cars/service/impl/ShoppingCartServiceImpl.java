package com.friendlyCarsSystem.friendly_cars.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.friendlyCarsSystem.friendly_cars.entity.Client;
import com.friendlyCarsSystem.friendly_cars.entity.ShoppingCart;
import com.friendlyCarsSystem.friendly_cars.entity.Vehicle;
import com.friendlyCarsSystem.friendly_cars.exception.ClientNotFoundException;
import com.friendlyCarsSystem.friendly_cars.exception.ShoppingCartNotFoundException;
import com.friendlyCarsSystem.friendly_cars.exception.VehicleNotFoundException;
import com.friendlyCarsSystem.friendly_cars.repository.ClientRepository;
import com.friendlyCarsSystem.friendly_cars.repository.ShoppingCartRepository;
import com.friendlyCarsSystem.friendly_cars.repository.VehicleRepository;
import com.friendlyCarsSystem.friendly_cars.service.ShoppingCartService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

/**
 * ShoppingCartServiceImpl
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;
    private ClientRepository clientRepository;
    private VehicleRepository vehicleRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
            ClientRepository clientRepository, VehicleRepository vehicleRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.clientRepository = clientRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public ShoppingCart createShoppingCart(String clientId, ShoppingCart shoppingCart)
        throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with '%s' not found",
                            clientId)));
        client.setShoppingCart(shoppingCart); // replace for an empty shopping cart
        clientRepository.save(client);
        return client.getShoppingCart();
    }

    @Override
    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public ShoppingCart getShoppingCartById(long shoppingCartId)
        throws ShoppingCartNotFoundException {
        ShoppingCart cart = shoppingCartRepository.findById(shoppingCartId)
            .orElseThrow(() -> new ShoppingCartNotFoundException(
                        String.format("Shopping Cart identified with '%d' not found",
                            shoppingCartId)));
        return cart;
    }

    @Override
    public ShoppingCart getShoppingCartClientId(String clientId)
        throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with '%s' not found",
                            clientId)));
        return client.getShoppingCart();
    }

    @Override
    public ShoppingCart updateShoppingCart(long shoppingCartId, ShoppingCart updatedShoppingCart)
            throws ShoppingCartNotFoundException {
        ShoppingCart cart = shoppingCartRepository.findById(shoppingCartId)
            .orElseThrow(() -> new ShoppingCartNotFoundException(
                        String.format("Shopping Cart identified with '%d' not found",
                            shoppingCartId)));
        // cart.setCartId(updatedShoppingCart.getCartId());
        cart.setClient(updatedShoppingCart.getClient());
        cart.setVehicles(updatedShoppingCart.getVehicles());
        return shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart partialUpdateShoppingCart(long shoppingCartId,
            Map<Object, Object> fields) throws ShoppingCartNotFoundException {
        ShoppingCart cart = shoppingCartRepository.findById(shoppingCartId)
            .orElseThrow(() -> new ShoppingCartNotFoundException(
                        String.format("Shopping Cart identified with '%d' not found",
                            shoppingCartId)));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(ShoppingCart.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, cart, value);
        });

        return shoppingCartRepository.save(cart);
    }

    @Override
    public ResponseEntity<String> deleteShoppingCart(long shoppingCartId)
        throws ShoppingCartNotFoundException {
        ShoppingCart cart = shoppingCartRepository.findById(shoppingCartId)
            .orElseThrow(() -> new ShoppingCartNotFoundException(
                        String.format("Shopping Cart identified with '%d' not found",
                            shoppingCartId)));
        shoppingCartRepository.delete(cart);
        return ResponseEntity.ok(String.format("ShoppingCart '%d' deleted",
                    shoppingCartId));
    }

    @Override
    public ShoppingCart dropVehicleOfShoppingCart(long shoppingCartId,
            long vehicleId) throws Exception {
        ShoppingCart cart = shoppingCartRepository.findById(shoppingCartId)
            .orElseThrow(() -> new ShoppingCartNotFoundException(
                        String.format("Shopping Cart identified with '%d' not found",
                            shoppingCartId)));
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new VehicleNotFoundException(
                        String.format("Vehicle Identified with '%s' not found",
                            vehicleId)));
        vehicle.setShoppingCart(null);
        cart.getVehicles().remove(vehicle);
        return shoppingCartRepository.save(cart);
    }
}
