import { useEffect, useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import loginImg from '../img/login_img.png'

import AuthService from '../service/AuthService'

const LoginComponent = (props) => {
    let [userName, setUserName] = useState('')
    let [userPassword, setUserPassword] = useState('')
    let [clientFound, setClientFound] = useState(true)
    let navigate = useNavigate()

    useEffect(() => {
        if(localStorage.getItem("isClientAthenticated")) navigate("/home")
    }, [])

    const findClient = (e) => {
        e.preventDefault()
        AuthService.loginClient(userName, userPassword)
            .then(response => {
                localStorage.setItem("loggedClientId", response.clientId)
                localStorage.setItem("isClientAthenticated", true)
                localStorage.setItem("userName", response.userName)
                localStorage.setItem("shoppingCartId", response.shoppingCart.cartId)
                setClientFound(true)
                navigate(`/home`)
            })
            .catch(error => {
                setClientFound(false)
                console.error(error)
            })
    }

    return (
        <div>
            <header>
                <nav className = "navbar navbar-expand-md" style={{backgroundColor: "#4A306D"}}>
                    <h2 style={{color: "White", margin: "auto"}}>Pagina de Ingreso</h2>
                </nav>
            </header>
            <br/>
            <br/>
            <div className="card col-md-6 offset-md-3">
                <div className = "card-body row">
                    <div className = "col mx-3">
                        <img src={loginImg} alt="login.png" style={{width: "100%"}} />
                    </div>
                    <div className = "col mt-4">
                        {!clientFound &&
                        <div className="alert alert-danger"> Usuario No Encontrado </div>
                        }
                        <form>
                          <div className="mb-3">
                            <label htmlFor="userName" className="form-label">Nombre de Usuario</label>
                            <input
                                type="text"
                                className="form-control"
                                onChange = {(e) => setUserName(e.target.value)}
                            />
                          </div>
                          <div className="mb-3">
                            <label htmlFor="userPassword" className="form-label">Contraseña</label>
                            <input
                                type="password"
                                className="form-control"
                                onChange = {(e) => setUserPassword(e.target.value)}
                            />
                          </div>
                          <button
                                type="submit"
                                className="btn btn-primary mb-3"
                                onClick = {(e) => findClient(e)}
                          >Ingresar</button>
                          <Link
                                to="/user-register"
                                type="submit"
                                className="btn btn-success mb-3 mx-2"
                          >Crear Usuario</Link>
                        </form>
                        <Link to="/updatepassword"> Recuperar Contraseña </Link>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default LoginComponent
