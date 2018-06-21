import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import SonarApi from "./SonarApi/SonarApi";
import StackApi from "./StackApi/StackApi";
import LoginModal from "./LoginModal/LoginModal";
import { Row, Col} from 'react-bootstrap';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            inputValue: 'Sample issue'
        };
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        this.setState({inputValue: event.target.inputValue});
    }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
            <h1 className="App-title">Welcome to SEE (sonar stackoverflow enhancer)</h1>
        </header>
        <p className="App-intro">
        </p><Col>
          <LoginModal/>
          <input id="userInput"value={this.state.inputValue} onChange={this.handleChange}/></Col>
          <Row className="show-grid">
              <Col xs={6} md={6}>
                  <SonarApi/>
              </Col>
              <Col xs={6} md={6}>
                  <StackApi/>
              </Col>
          </Row>
      </div>

    );
  }
}

export default App;