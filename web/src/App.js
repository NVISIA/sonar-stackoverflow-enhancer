import React, { Component } from 'react';
import logo from './NVISIA-Logo.png';
import './App.css';
import SonarApi from "./SonarApi/SonarApi";
import StackApi from "./StackApi/StackApi";
import LoginModal from "./LoginModal/LoginModal";
import LoginManager from "./LoginModal/LoginManager"
import NavBar from "./NavigationBar/NavBar"
import { Row, Col, Nav, Navbar, NavItem, NavDropdown, MenuItem} from 'react-bootstrap';

class App extends Component {
    render() {
    return (
      <div className="App">
          <NavBar/>
        <header className="App-header">
            <h1 className="App-title">Welcome to SEE (sonar stackoverflow enhancer)</h1>
        </header>
        <p className="App-intro">
        </p>
          <Row className="show-grid">
              <Col xs={3} md={3}></Col>
              <Col xs={6} md={6}>
                  <LoginModal/>
              </Col>
              <Col xs={3} md={3}></Col>
          </Row>
      </div>

    );
  }
}

export default App;