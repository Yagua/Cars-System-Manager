package com.csManager.csmanager.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.csManager.csmanager.entity.Client;
import com.csManager.csmanager.entity.ShoppingCart;
import com.csManager.csmanager.entity.Vehicle;
import com.csManager.csmanager.exception.ClientNotFoundException;
import com.csManager.csmanager.exception.ShoppingCartNotFoundException;
import com.csManager.csmanager.exception.VehicleNotFoundException;
import com.csManager.csmanager.repository.ClientRepository;
import com.csManager.csmanager.repository.ShoppingCartRepository;
import com.csManager.csmanager.repository.VehicleRepository;
import com.csManager.csmanager.service.ShoppingCartService;

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
    public ResponseEntity<ShoppingCart> createShoppingCart(String clientId, ShoppingCart shoppingCart)
        throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with '%s' not found",
                            clientId)));
        client.setShoppingCart(shoppingCart); // replace for an empty shopping cart
        clientRepository.save(client);
        ShoppingCart clientCart = client.getShoppingCart();
        return ResponseEntity.ok(clientCart);
    }

    @Override
    public ResponseEntity<List<ShoppingCart>> getAllShoppingCarts() {
        List<ShoppingCart> carts = shoppingCartRepository.findAll();
        return ResponseEntity.ok(carts);
    }

    @Override
    public ResponseEntity<ShoppingCart> getShoppingCartById(long shoppingCartId)
        throws ShoppingCartNotFoundException {
        ShoppingCart cart = shoppingCartRepository.findById(shoppingCartId)
            .orElseThrow(() -> new ShoppingCartNotFoundException(
                        String.format("Shopping Cart identified with '%d' not found",
                            shoppingCartId)));

        return ResponseEntity.ok(cart);
    }

    @Override
    public ResponseEntity<ShoppingCart> getShoppingCartClientId(String clientId)
        throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Client identified with '%s' not found",
                            clientId)));
        ShoppingCart cart = client.getShoppingCart();
        return ResponseEntity.ok(cart);
    }

    @Override
    public ResponseEntity<ShoppingCart> updateShoppingCart(long shoppingCartId, ShoppingCart updatedShoppingCart)
            throws ShoppingCartNotFoundException {
        ShoppingCart cart = shoppingCartRepository.findById(shoppingCartId)
            .orElseThrow(() -> new ShoppingCartNotFoundException(
                        String.format("Shopping Cart identified with '%d' not found",
                            shoppingCartId)));
        // cart.setCartId(updatedShoppingCart.getCartId());
        cart.setClient(updatedShoppingCart.getClient());
        cart.setVehicles(updatedShoppingCart.getVehicles());

        ShoppingCart savedCart = shoppingCartRepository.save(cart);
        return ResponseEntity.ok(savedCart);
    }

    @Override
    public ResponseEntity<ShoppingCart> partialUpdateShoppingCart(long shoppingCartId,
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

        ShoppingCart updatedCart = shoppingCartRepository.save(cart);

        return ResponseEntity.ok(updatedCart);
    }

    @Override
    public ResponseEntity<?> deleteShoppingCart(long shoppingCartId)
        throws ShoppingCartNotFoundException {
        ShoppingCart cart = shoppingCartRepository.findById(shoppingCartId)
            .orElseThrow(() -> new ShoppingCartNotFoundException(
                        String.format("Shopping Cart identified with '%d' not found",
                            shoppingCartId)));
        shoppingCartRepository.delete(cart);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ShoppingCart> dropVehicleOfShoppingCart(long shoppingCartId,
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
        ShoppingCart savedCart  = shoppingCartRepository.save(cart);
        return ResponseEntity.ok(savedCart);
    }
}
