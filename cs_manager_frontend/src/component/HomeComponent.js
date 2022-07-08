import { useEffect, useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'

import HeaderComponent from '../component/HeaderComponent'
import ClientService from '../service/ClientService'
import VehicleListComponent from '../component/VehicleListComponent'

const HomeComponent = (props) => {
    let [client, setClient] = useState({});
    let [isLoaded, setIsLoaded] = useState(false);
    let userId = localStorage.getItem("loggedClientId");
    let navigate = useNavigate();

    useEffect(() => {
        if(!localStorage.getItem("isClientAthenticated")) navigate("/login")
        ClientService.getClientById(userId)
            .then((client) => {
                setClient(client);
                setIsLoaded(true);
            })
            .catch(error => console.error(error))
    }, [])

    const renderContent = () => {
        if(!isLoaded) return <></>
        return (
            <div>
                <div className = "sticky-top bg-light">
                    <HeaderComponent onHome = {true} />
                </div>
                <VehicleListComponent
                    onHome = {true}
                    shoppingCart = {client.shoppingCart}
                />
              <div className = "bubbleCart d-flex justify-content-end">
                 <Link to = "/cart" class="btn btn-primary position-relative mx-3 mb-3" >
                      <i class="bi bi-cart2"></i>
                    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                      {client.shoppingCart.vehicles.length}
                      <span class="visually-hidden"></span>
                    </span>
                  </Link>
              </div>
            </div>
        );
    }

    return renderContent()
}

export default HomeComponent
