import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import VehicleService from '../service/VehicleService'
import defCarImg from '../img/defCarImg.png'

const VehicleListComponent = (props) => {
    let [isLoaded, setIsLoaded] = useState(false);
    let [vehicles, setVehicles] = useState([]);
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

    const renderContent = () => {
        if(!isLoaded) return <></>
        return (
            <div className="m-4 row">
                {vehicles.map((vehicle) => {
                    let imgSrc = vehicle.image
                        ? `data:${vehicle.image.imageType};base64,${vehicle.image.imageContent}`
                        : defCarImg
                    let imgAlt = vehicle.image ? vehicle.image.imageName : "default.png"

                    return (
                        <div className="card m-3" style={{width: "18rem"}}>
                             <img src={imgSrc}
                                className="card-img-top" alt={imgAlt}
                                style={{height: "12rem", objectFit: "cover"}}
                            />
                             <div className="card-body">

                                 <div className="accordion accordion-flush" id="accordionFlushExample">
                                   <div className="accordion-item">
                                     <h2 className="accordion-header" id="flush-headingOne">
                                       <button className="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                                         Accordion Item #1
                                       </button>
                                     </h2>
                                     <div id="flush-collapseOne" className="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                                       <div className="accordion-body">Placeholder content for this accordion, which is intended to demonstrate the <code>.accordion-flush</code> class. This is the first item's accordion body.</div>
                                     </div>
                                   </div>
                                   <div className="accordion-item"></div>
                                 </div>

                                 <h4 className="card-title text-center">{vehicle.model}</h4>
                                    <ul className="list-group list-group-flush">
                                      <li className="list-group-item px-0">
                                        <span className="fw-bold">Disponible: </span>{`${vehicle.available ? "Si" : "No"}`}
                                      </li>
                                      <li className="list-group-item px-0">A second item</li>
                                      <li className="list-group-item px-0">A third item</li>
                                      <li className="list-group-item px-0">A fourth item</li>
                                      <li className="list-group-item px-0">And a fifth one</li>
                                    </ul>
                                 <a href="#" className="btn btn-primary my-2">Go somewhere</a>
                             </div>
                        </div>
                    );
                })
                }
            </div>
        );
    }

    return renderContent()
}

export default VehicleListComponent
