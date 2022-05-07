import {Link} from 'react-router-dom'

const HeaderComponent = (props) => {
    return (
        <div className="sticky-top">
            <nav className = "navbar">
                <h3 className="navbar-brand justify-content-start text-white mx-3"
                >{props.navBarBrand ? props.navBarBrand : "Carros Amistosos"}</h3>

                <ul className="nav mx-3">
                    <div className="dropdown">
                      <button
                            className="btn btn-primary dropdown-toggle"
                            type="button"
                            id="dropdownMenuButton1"
                            data-bs-toggle="dropdown"
                            aria-expanded="false"
                            style={{backgroundColor: "#73937E"}}
                      >
                        <i className="bi bi-card-list"> </i>
                      </button>

                      <ul className="dropdown-menu dropdown-menu-sm-end" aria-labelledby="dropdownMenuButton1">
                      {!props.onHome ?
                        <li>
                            <Link
                                to = "/home"
                                className="dropdown-item"
                            >Atras</Link>
                        </li>
                      :
                          <>
                            <li>
                                <Link
                                    to = "/profile"
                                    className="dropdown-item"
                                >Perfil</Link>
                            </li>
                          </>
                      }
                          <li>
                            <Link
                                to = "/login"
                                className="dropdown-item"
                                onClick={() => {
                                    localStorage.removeItem("loggedClientId")
                                    localStorage.removeItem("isClientAthenticated")
                                    localStorage.removeItem("userName")
                                }}
                            >Salir</Link>
                          </li>
                      </ul>
                    </div>
                </ul>
            </nav>
        </div>
    );
}

export default HeaderComponent
