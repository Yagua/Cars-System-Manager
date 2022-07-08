import { useState, useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { Modal, ModalBody, ModalFooter, ModalHeader } from "react-bootstrap";

import ClientService from '../service/ClientService'
import { checkValidInput } from '../lib/util'

const RegisterCompenent = (props) => {
    let [clientId, setClientId] = useState('')
    let [userName, setUserName] = useState('')
    let [firstName, setFirstName] = useState('')
    let [secondName, setSecondName] = useState('')
    let [paternalLastName, setPaternalLastName] = useState('')
    let [maternalLastName, setMaternalLastName] = useState('')
    let [address, setAddress] = useState('')
    let [phoneNumber, setPhoneNumber] = useState('')
    let [password, setPassword] = useState('')
    let [confirmedPassword, setConfirmedPassword] = useState('')
    let [errorMessage, setErrorMessage] = useState('')
    let [modalBody, setModalBody] = useState('')
    let [showModal, setShowModal] = useState(false)

    let [validUserName, setValidUserName] = useState(false)
    let [validFirstName, setValidFirstName] = useState(false)
    let [validSecondName, setValidSecondName] = useState(false)
    let [validPaternalLastName, setValidPaternalLastName] = useState(false)
    let [validMaternalLastName, setValidMaternalLastName] = useState(false)
    let [validPhoneNumber, setValidPhoneNumber] = useState(false)
    let [validAddress, setValidAddress] = useState(false)
    let [validPassword, setValidPassword] = useState(false)
    let [validConfirmedPassword, setValidConfirmedPassword] = useState(false)
    let [validClientId, setValidClientId] = useState(false)

    let isValidRequest = (
        validFirstName &&
        validClientId &&
        validSecondName &&
        validPaternalLastName &&
        validMaternalLastName &&
        validUserName &&
        validPassword &&
        validPhoneNumber &&
        validAddress &&
        validConfirmedPassword
    )

    let navigate = useNavigate()

    const clientTemplate = {
        clientId: clientId,
        userName: userName,
        firstName: firstName,
        secondName: secondName,
        paternalLastName: paternalLastName,
        maternalLastName: maternalLastName,
        address: address,
        telephoneNumber: phoneNumber,
        password: password,
        invoices: []
    }

    const registerUser = () => {
        console.log(clientTemplate)
        if(password !== confirmedPassword) {
            setErrorMessage("Password do not match!")
            setValidPassword(false)
            setValidConfirmedPassword(false)
            return
        }

        ClientService.createClient(clientTemplate)
            .then(_ => {
                navigate("/login")
            })
            .catch(error => {
                setModalBody(`The user "${userName}" already exist`)
                setShowModal(true)
                setValidUserName(false)
                console.error(error)
            })

    }

    return (
        <div>
           <div className="sticky-top">
               {!props.disableNavBar &&
               <nav className = "navbar text-white">
                   <h3 className="navbar-brand justify-content-start"
                       style={{marginLeft:"20px"}}
                   >User Registration</h3>
               </nav>
               }
           </div>
           <Modal
               show = {showModal}
               aria-labelledby="contained-modal-title-vcenter"
               centered
           >
               <ModalHeader>User Registration</ModalHeader>
               <ModalBody>{modalBody}</ModalBody>
               <ModalFooter>
                   <button
                       className="btn btn-secondary"
                       onClick = {() => setShowModal(false)}
                   >Close</button>
               </ModalFooter>
           </Modal>
           <div className = "container my-4">
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3 offset-md-3 border">
                        <div className = "card-body">
                            <form>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Id</label>
                                    <input
                                        type = "text"
                                        maxLength = {20}
                                        placeholder = "Enter User Id"
                                        className = {`form-control ${validClientId ? "is-valid" : "is-invalid"}`}
                                        onChange = {(e) => {
                                            let value = e.target.value
                                            setValidClientId(checkValidInput(value))
                                            setClientId(value)
                                        }}
                                    >
                                    </input>
                                </div>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> User Name</label>
                                    <input
                                        type = "text"
                                        maxLength = {20}
                                        placeholder = "Enter User Name"
                                        className = {`form-control ${validUserName ? "is-valid" : "is-invalid"}`}
                                        onChange = {(e) => {
                                            let value = e.target.value
                                            setValidUserName(checkValidInput(value))
                                            setUserName(value)
                                        }}
                                    >
                                    </input>
                                </div>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> First Name</label>
                                    <input
                                        type = "text"
                                        maxLength = {20}
                                        placeholder = "Enter First Name"
                                        className = {`form-control ${validFirstName ? "is-valid" : "is-invalid"}`}
                                        onChange = {(e) => {
                                            let value = e.target.value
                                            setValidFirstName(checkValidInput(value))
                                            setFirstName(value)}}
                                    >
                                    </input>
                                </div>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Second Name</label>
                                    <input
                                        type = "text"
                                        maxLength = {20}
                                        placeholder = "Enter Second Name"
                                        className = {`form-control ${validSecondName ? "is-valid" : "is-invalid"}`}
                                        onChange = {(e) => {
                                            let value = e.target.value
                                            setValidSecondName(checkValidInput(value))
                                            setSecondName(value)
                                        }}
                                    >
                                    </input>
                                </div>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Paternal Last Name</label>
                                    <input
                                        type = "text"
                                        maxLength = {20}
                                        placeholder = "Enter Paternal Last Name"
                                        className = {`form-control ${validPaternalLastName ? "is-valid" : "is-invalid"}`}
                                        onChange = {(e) => {
                                            let value = e.target.value
                                            setValidPaternalLastName(checkValidInput(value))
                                            setPaternalLastName(value)
                                        }}
                                    >
                                    </input>
                                </div>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Maternal Last Name </label>
                                    <input
                                        type = "text"
                                        maxLength = {20}
                                        placeholder = "Enter Maternal Last Name"
                                        className = {`form-control ${validMaternalLastName ? "is-valid" : "is-invalid"}`}
                                        onChange = {(e) => {
                                            let value = e.target.value
                                            setValidMaternalLastName(checkValidInput(value))
                                            setMaternalLastName(value)
                                        }}
                                    >
                                    </input>
                                </div>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Address</label>
                                    <input
                                        type = "text"
                                        maxLength = {20}
                                        placeholder = "Enter Adress"
                                        className = {`form-control ${validAddress ? "is-valid" : "is-invalid"}`}
                                        onChange = {(e) => {
                                            let value = e.target.value
                                            setValidAddress(checkValidInput(value))
                                            setAddress(value)
                                        }}
                                    >
                                    </input>
                                </div>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Phone Number</label>
                                    <input
                                        type = "text"
                                        maxLength = {20}
                                        placeholder = "Enter Phone Number"
                                        className = {`form-control ${validPhoneNumber ? "is-valid" : "is-invalid"}`}
                                        onChange = {(e) => {
                                            let value = e.target.value
                                            setValidPhoneNumber(checkValidInput(value))
                                            setPhoneNumber(value)
                                        }}
                                    >
                                    </input>
                                </div>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> New Password</label>
                                    <input
                                        type = "password"
                                        maxLength = {50}
                                        placeholder = "Enter New Password"
                                        className = {`form-control ${validPassword ? "is-valid" : "is-invalid"}`}
                                        onChange = {(e) => {
                                            let value = e.target.value
                                            setValidPassword(checkValidInput(value))
                                            setPassword(value)
                                        }}
                                    >
                                    </input>
                                </div>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Confirm Password</label>
                                    <input
                                        type = "password"
                                        maxLength = {50}
                                        placeholder = "Confirm Password"
                                        className = {`form-control ${validConfirmedPassword ? "is-valid" : "is-invalid"}`}
                                        onChange = {(e) => {
                                            let value = e.target.value
                                            setValidConfirmedPassword(checkValidInput(value))
                                            setConfirmedPassword(value)
                                        }}
                                    >
                                    </input>
                                    { !isValidRequest &&
                                        <p className="invalid-feedback"> {errorMessage}</p>
                                    }
                                </div>
                                <buttom
                                    className={`btn ${isValidRequest ? "btn-success" : "btn-secondary"}`}
                                    onClick = {isValidRequest ? () => {registerUser()} : null}
                                > Register</buttom>
                                <Link to="/login" className="btn btn-danger m-2"> Cancel </Link>
                            </form>
                        </div>
                    </div>
                </div>
           </div>
        </div>
    );
}

export default RegisterCompenent
