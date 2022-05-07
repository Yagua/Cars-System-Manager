import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

import HeaderComponent from '../component/HeaderComponent'
import ClientService from '../service/ClientService'
import VehicleListComponent from '../component/VehicleListComponent'

const HomeComponent = () => {
    let [client, setClient] = useState({});
    let [isLoaded, setIsLoaded] = useState(false);
    let userId = localStorage.getItem("loggedClientId");
    let navigate = useNavigate()

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
                    <HeaderComponent onHome = {true}/>
                </div>
                <VehicleListComponent />
            </div>
        );
    }

    return renderContent()
}

export default HomeComponent
