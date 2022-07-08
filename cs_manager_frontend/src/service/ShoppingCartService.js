import axios from "axios";

const SHOPPING_CART_BASE_URL = "http://localhost:8080/api/v1/carts";

class ShoppingCartService {
    createShoppingCart(clientId, shoppingCart) {
        return axios.post(SHOPPING_CART_BASE_URL + `/c/${clientId}`, shoppingCart)
            .then(response => response.data)
    }

    getAllShoppingCarts() {
        return axios.get(SHOPPING_CART_BASE_URL)
            .then(response => response.data)
    }

    getShoppingCartById(shoppingCartId) {
        return axios.get(SHOPPING_CART_BASE_URL + `/${shoppingCartId}`)
            .then(response => response.data)
    }

    getShoppingCartByClientId(clientId) {
        return axios.get(SHOPPING_CART_BASE_URL + `/c/${clientId}`)
            .then(response => response.data)
    }

    updateShoppingCart(shoppingCartId, newShoppingCart) {
        return axios.put(SHOPPING_CART_BASE_URL + `/${shoppingCartId}`, newShoppingCart)
            .then(response => response.data)
    }

    partialUpdateShoppingCart(shoppingCartId, newShoppingCart) {
        return axios.patch(SHOPPING_CART_BASE_URL + `/${shoppingCartId}`, newShoppingCart)
            .then(response => response.data)
    }

    deleteShoppingCart(shoppingCartId) {
        return axios.delete(SHOPPING_CART_BASE_URL + `/${shoppingCartId}`)
            .then(response => response.data)
    }

    dropVehicleOfShoppingCart(shoppingCartId, vehicleId) {
        return axios.post(SHOPPING_CART_BASE_URL + `/${shoppingCartId}/vh/${vehicleId}`)
            .then(response => response.data)
    }
}

export default new ShoppingCartService()
