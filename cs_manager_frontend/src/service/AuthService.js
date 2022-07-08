import axios from 'axios'

const LOGIN_BASE_URL = "http://localhost:8080/api/v1/auth"

class AuthService {

    loginClient(clientName, clientPassword) {
        const clientCredentials = {
            userName: clientName,
            password: clientPassword
        }
        return axios.post(LOGIN_BASE_URL + "/login", clientCredentials)
            .then(client => client.data)
    }
}

export default new AuthService()
