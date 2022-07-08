import {Link} from 'react-router-dom'

import Dropdown from 'react-bootstrap/Dropdown'

const HeaderComponent = (props) => {
    return (
        <div className="sticky-top">
            <nav className = "navbar">
                <h3 className="navbar-brand text-white mx-3 d-flex justify-content-end"
                    style={{width: "55%"}}
                >{props.navBarBrand ? props.navBarBrand : "Cars System Manager"}</h3>


                <Dropdown className="mx-3">

                  <Dropdown.Toggle id="dropdown-basic">
                    <i className="bi bi-card-list"></i>
                  </Dropdown.Toggle>

                  <Dropdown.Menu>
                  {!props.onHome ?
                      <Dropdown.Item>
                          <Link
                              to = "/home"
                              className="dropdown-item"
                          >Back</Link>
                      </Dropdown.Item>
                      :
                      <>
                      <Dropdown.Item>
                          <Link
                              to = "/profile"
                              className="dropdown-item"
                          >Profile</Link>
                      </Dropdown.Item>
                      <Dropdown.Item>
                          <Link
                              to = "/login"
                              className="dropdown-item"
                              onClick={() => {
                                  localStorage.removeItem("loggedClientId")
                                  localStorage.removeItem("isClientAthenticated")
                                  localStorage.removeItem("userName")
                              }}
                          >Log Out</Link>
                      </Dropdown.Item>
                      </>
                  }
                  </Dropdown.Menu>
                </Dropdown>
            </nav>
        </div>
    );
}

export default HeaderComponent
