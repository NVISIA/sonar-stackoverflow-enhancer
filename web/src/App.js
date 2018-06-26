import React, {Component} from 'react';
import './App.css';
import LoginModal from "./LoginModal/LoginModal";
import NavBar from "./NavigationBar/NavBar"
import {Col, Row} from 'react-bootstrap';

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
              <Col xs={12} md={12}>
                  <LoginModal/>
              </Col>
          </Row>
      </div>

    );
  }
}

export default App;