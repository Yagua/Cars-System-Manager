import {useEffect, useState} from 'react'
import { Link, useNavigate } from 'react-router-dom'

import HeaderComponent from '../component/HeaderComponent'
import LoadingComponent from './LoadingComponent';
import ClientService from '../service/ClientService';
import profileImg from '../img/profile_img.png'

const ProfileComponent = () => {
    let [client, setClient] = useState({})
    let [isLoaded, setIsLoaded] = useState(false)
    let clientId = localStorage.getItem("loggedClientId")
    let navigate = useNavigate()

    useEffect(() => {
        if(!localStorage.getItem("isClientAthenticated")) navigate("/login")
        ClientService.getClientById(clientId)
            .then(response => {
                setClient(response);
                setIsLoaded(true)
            })
            .catch(error => {
                console.error(error)
                setIsLoaded(false)
            })
    }, [])

    const renderContent = () => {
        return (
            <>
                <HeaderComponent
                    onHome={false}
                    navBarBrand = "Perfil de Cliente"
                />
                {!isLoaded ?
                    <LoadingComponent />
                    :
                <div className="my-5">
                    <div className="card col-md-6 offset-md-3">
                      <div className="row">
                        <div className="col">
                          <img src={profileImg} className="img-fluid rounded-start" alt="profile_img.png" />
                        </div>
                        <div className="col border-start">
                          <div className="card-body">
                             <div className="m-3">
                                 <h5><strong>Id de Usuario: </strong>{client.clientId}</h5>
                                 <h5><strong>Nombres: </strong>{client.firstName} {client.secondName}</h5>
                                 <h5><strong>Apellidos: </strong>{client.paternalLastName} {client.maternalLastName}</h5>
                                 <h5><strong>Nombre de usuario: </strong>{client.userName}</h5>
                                 <h5><strong>Numero de Celular: </strong>{client.telephoneNumber}</h5>
                             </div>
                             <br/>
                             <Link
                                 className="btn btn-success mx-auto d-block"
                                 to = "/updateinfo"
                             > Actualizar Datos</Link>
                             <buttom
                                 className="btn btn-secondary mx-auto d-block my-2"
                                 onClick = {() => {}}
                             > Eliminar Cliente</buttom>
                          </div>
                        </div>
                      </div>
                    </div>
                </div>
                }
            </>
        );
    }

    return renderContent();
}

export default ProfileComponent;
