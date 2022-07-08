import axios from "axios";

const VEHICLE_BASE_URL = "http://localhost:8080/api/v1/vehicles";

class VehicleService {
    createVehicle(vehicle) {
        return axios.post(VEHICLE_BASE_URL, vehicle)
            .then(response => response.data)
    }

    getAllVehicles() {
        return axios.get(VEHICLE_BASE_URL)
            .then(response => response.data)
    }

    getVehicleById(vehicleId) {
        return axios.get(VEHICLE_BASE_URL + `/${vehicleId}`)
            .then(response => response.data)
    }

    updateVehicle(vehicleId, newVehicle) {
        return axios.put(VEHICLE_BASE_URL + `/${vehicleId}`, newVehicle)
            .then(response => response.data)
    }

    partialUpdateVehicle(vehicleId, newVehicle) {
        return axios.patch(VEHICLE_BASE_URL + `/${vehicleId}`, newVehicle)
            .then(response => response.data)
    }

    deleteVehicle(vehicleId) {
        return axios.delete(VEHICLE_BASE_URL + `/${vehicleId}`)
            .then(response => response.data)
    }

    addVehicleToShoppingCart(vehicleId, shoppingCartId) {
        return axios.post(VEHICLE_BASE_URL + `/${vehicleId}/sc/${shoppingCartId}`, {})
            .then(response => response.data)
    }
}

export default new VehicleService()
