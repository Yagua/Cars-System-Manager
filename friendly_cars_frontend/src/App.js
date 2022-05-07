import './App.css';

import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'

import LoginComponent from './component/LoginComponent'
import HomeComponent from './component/HomeComponent'
import ProfileComponent from './component/ProfileComponent'
import LoadingComponent from './component/LoadingComponent'

function App() {
  return (
      <Router>
        <Routes>
            <Route exact path="/" element={<HomeComponent />} />
            <Route exact path="/home" element={<HomeComponent />} />
            <Route exact path="/login" element={<LoginComponent />} />
            <Route exact path="/profile" element={<ProfileComponent />} />
            <Route exact path="/load" element={<LoadingComponent />} />
        </Routes>
      </Router>
  );
}

export default App;
