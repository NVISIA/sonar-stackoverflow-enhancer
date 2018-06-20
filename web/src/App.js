import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import SonarApi from "./SonarApi"

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            inputValue: 'Gradle issue'
        };
    }
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
          <input id="userInput" value={this.state.inputValue}/>
          <button onClick={callApi()}>
              Resolve issues from sonar
          </button>
          <button onClick={test}>
              Get issues from sonar
          </button>
          <SonarApi/>
      </div>

    );
  }
}

function test() {
    console.log("Button press" + Math.random(0,100))
}

function callApi(value) {
    console.log(value);
}

function ActionLink() {
    function handleClick(e) {
        e.preventDefault();
        console.log('The link was clicked.');
    }}

export default App;