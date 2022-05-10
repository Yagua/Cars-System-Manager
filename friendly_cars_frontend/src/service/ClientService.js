import axios from "axios";

const CLIENT_BASE_URL = "http://localhost:8080/api/v1/clients";

class ClientService {
    createClient(client) {
        return axios.post(CLIENT_BASE_URL, client)
            .then(response => response.data)
    }

    getAllClients() {
        return axios.get(CLIENT_BASE_URL)
            .then(response => response.data)
    }

    getClientById(clientId) {
        return axios.get(CLIENT_BASE_URL + `/${clientId}`)
            .then(response => response.data)
    }

    updateClient(clientId, newClient) {
        return axios.put(CLIENT_BASE_URL + `/${clientId}`, newClient)
            .then(response => response.data)
    }

    partialUpdateClient(clientId, newClient) {
        return axios.patch(CLIENT_BASE_URL + `/${clientId}`, newClient)
            .then(response => response.data)
    }

    deleteClient(clientId) {
        return axios.delete(CLIENT_BASE_URL + `/${clientId}`)
            .then(response => response.data)
    }
}

export default new ClientService()
