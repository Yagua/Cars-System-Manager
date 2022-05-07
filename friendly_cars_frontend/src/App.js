import './App.css';

import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'

import LoginComponent from './component/LoginComponent'
import HomeComponent from './component/HomeComponent'

function App() {
  return (
      <Router>
        <Routes>
            <Route exact path="/" element={<HomeComponent />} />
            <Route exact path="/home" element={<HomeComponent />} />
            <Route exact path="/login" element={<LoginComponent />} />
        </Routes>
      </Router>
  );
}

export default App;
