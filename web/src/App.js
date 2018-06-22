import React, { Component } from 'react';
import logo from './NVISIA-Logo.png';
import './App.css';
import SonarApi from "./SonarApi/SonarApi";
import StackApi from "./StackApi/StackApi";
import LoginModal from "./LoginModal/LoginModal";
import { Row, Col} from 'react-bootstrap';

class App extends Component {
    render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
            <h1 className="App-title">Welcome to SEE (sonar stackoverflow enhancer)</h1>
        </header>
        <p className="App-intro">
        </p>
          <Row className="show-grid">
              <Col xs={6} md={6}>
                  <SonarApi/>
              </Col>
              <Col xs={6} md={6}>
                  <LoginModal/>
              </Col>
          </Row>
      </div>

    );
  }
}

export default App;