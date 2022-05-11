import { useEffect, useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import Accordion from 'react-bootstrap/Accordion'

import VehicleService from '../service/VehicleService'
import ShoppingCartService from '../service/ShoppingCartService'
import FooterComponent from '../component/FooterComponent'
import LoadingComponent from './LoadingComponent'
import defCarImg from '../img/defCarImg.png'

const VehicleListComponent = (props) => {
    let [isLoaded, setIsLoaded] = useState(false);
    let [vehicles, setVehicles] = useState([]);
    let shoppingCartId = localStorage.getItem("shoppingCartId")
    let navigate = useNavigate()

    useEffect(() => {
        if(!localStorage.getItem("isClientAthenticated")) navigate("/login")
        VehicleService.getAllVehicles()
            .then((vehicles) => {
                setVehicles(vehicles);
                setIsLoaded(true);
            })
            .catch(error => console.error(error))
    }, [])

    const addVehicleToShoopingCart = (vehicleId) => {
        VehicleService.addVehicleToShoppingCart(vehicleId,
            shoppingCartId)
            .then(_ => {
                VehicleService.partialUpdateVehicle(
                    vehicleId, { available: false })
                    .then(() => window.location.reload())
                    .catch(error => console.error(error))
            })
            .catch(error => console.error(error))
    }

    const dropVehicloOfShoppingCart = (vehicleId) => {
        ShoppingCartService.dropVehicleOfShoppingCart(shoppingCartId, vehicleId)
            .then(_ => {
                VehicleService.partialUpdateVehicle(
                    vehicleId, { available: true })
                    .then(window.location.reload())
                    .catch(error => console.error(error))
            })
            .catch(error => console.error(error))
    }

    const renderContent = () => {
        if(!isLoaded) return <LoadingComponent />
        let elements = props.vehicles ? props.vehicles : vehicles
        return (
            <>
                <div className="m-2 row d-flex justify-content-center" style={{backgroundColor: "#EDE7EF"}}>
                    {elements.map((vehicle) => {
                        let imgSrc = vehicle.image
                            ? `data:${vehicle.image.imageType};base64,${vehicle.image.imageContent}`
                            : defCarImg
                        let imgAlt = vehicle.image ? vehicle.image.imageName : "default.png"

                        return (
                            <div className="card m-3 p-0" style={{width: "18rem", height: "100%"}}>
                                 <img src={imgSrc}
                                    className="card-img-top" alt={imgAlt}
                                    style={{height: "12rem", objectFit: "cover"}}
                                />
                                 <div className="card-body">
                                     <Accordion flush>
                                       <Accordion.Item eventKey={vehicle.vehicleId}>
                                         <Accordion.Header>
                                             <h5 className="fst-italic">{vehicle.vehicleName}</h5>
                                         </Accordion.Header>
                                         <Accordion.Body>
                                            <ul className="list-group list-group-flush">
                                              <li className="list-group-item px-0" key={`lit-${vehicle.vehicleId}-0`}>
                                                <span className="fw-bold">Modelo: </span>{vehicle.model}
                                              </li>
                                              <li className="list-group-item px-0" key={`lit-${vehicle.vehicleId}-1`}>
                                                <span className="fw-bold">Disponible: </span>{`${vehicle.available ? "Si" : "No"}`}
                                              </li>
                                              <li className="list-group-item px-0" key={`lit-${vehicle.vehicleId}-2`}>
                                                <span className="fw-bold">Peso: </span>{vehicle.vehicleWeight} Kg
                                              </li>
                                              <li className="list-group-item px-0" key={`lit-${vehicle.vehicleId}-3`}>
                                                <span className="fw-bold">Lugar de Fabricación: </span>{vehicle.placeOfManufacture}
                                              </li>
                                              <li className="list-group-item px-0" key={`lit-${vehicle.vehicleId}-4`}>
                                                <span className="fw-bold">Numero de Puertas: </span>{vehicle.numberOfDoors}
                                              </li>
                                              <li className="list-group-item px-0" key={`lit-${vehicle.vehicleId}-5`}>
                                                <span className="fw-bold">Fabricante: </span>{vehicle.manufacturer}
                                              </li>
                                              <li className="list-group-item px-0" key={`lit-${vehicle.vehicleId}-6`}>
                                                <span className="fw-bold">Vendedor: </span>{vehicle.sellerName}
                                              </li>
                                            </ul>
                                         </Accordion.Body>
                                       </Accordion.Item>
                                       <Accordion.Item></Accordion.Item>
                                     </Accordion>
                                     <div className = "d-flex justify-content-center">
                                        {props.onHome ?
                                         <button
                                            className={`btn ${vehicle.available ? "btn-primary" : "btn-secondary"} mt-3`}
                                            onClick = {(e) => {
                                                if(vehicle.available) {
                                                    addVehicleToShoopingCart(vehicle.vehicleId, e)
                                                }
                                            }}
                                         >Agregar a Carrito</button>
                                            :
                                        <>
                                         <button
                                            className="btn btn-success mt-3"
                                            onClick = {() => {
                                                //some logic to delete this vehicle to the shoppingCart
                                            }}
                                         >Comprar</button>
                                         <button
                                            className="btn btn-danger mx-3 mt-3"
                                            onClick = {() => {
                                                dropVehicloOfShoppingCart(vehicle.vehicleId)
                                            }}
                                         >Eliminar</button>
                                        </>
                                        }
                                     </div>
                                 </div>
                            </div>
                        );
                    })
                    }
                </div>
                {!props.onHome &&
                <div
                    className="validateCart"
                    style={{bottom: "0", position: "fixed"}}
                >
                    <button type="button" className = "btn btn-success m-3">
                        Validar Carrito
                    </button>
                </div>
                }
            </>
        );
    }

    return renderContent()
}

export default VehicleListComponent
