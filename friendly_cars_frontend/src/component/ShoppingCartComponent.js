import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

import HeaderComponent from '../component/HeaderComponent'
import ShoppingCartService from '../service/ShoppingCartService'
import VehicleListComponent from './VehicleListComponent'

const ShoppingCartComponent = () => {
    let [shoppingCart, setShoppingCart] = useState({})
    let shoppingCartId = localStorage.getItem("shoppingCartId")
    let navigate = useNavigate()

    useEffect(() => {
        if(!localStorage.getItem("isClientAthenticated")) navigate("/login")
        ShoppingCartService.getShoppingCartById(shoppingCartId)
            .then(response => setShoppingCart(response))
            .catch(error => console.error(error))
    }, [])

    const renderContent = () => {
        return (
            <>
                <HeaderComponent
                    onHome = {false}
                    navBarBrand = {"Carrito de Compras"}
                />
                <VehicleListComponent vehicles = {shoppingCart.vehicles}/>
            </>
        );
    }
    return renderContent()
}

export default ShoppingCartComponent
