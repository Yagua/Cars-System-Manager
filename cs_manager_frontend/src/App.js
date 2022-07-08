import './App.css';

import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'

import LoginComponent from './component/LoginComponent'
import HomeComponent from './component/HomeComponent'
import ProfileComponent from './component/ProfileComponent'
import ShoppingCartComponent from './component/ShoppingCartComponent'
import RegisterComponent from './component/RegisterComponent'

function App() {
  return (
      <Router>
        <Routes>
            <Route exact path="/" element={<HomeComponent />} />
            <Route exact path="/home" element={<HomeComponent />} />
            <Route exact path="/login" element={<LoginComponent />} />
            <Route exact path="/profile" element={<ProfileComponent />} />
            <Route exact path="/cart" element={<ShoppingCartComponent/>} />
            <Route exact path="/user-register" element={<RegisterComponent/>} />
        </Routes>
      </Router>
  );
}

export default App;
