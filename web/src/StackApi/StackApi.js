import React, { Component } from 'react';
import '../App.css';

class StackApi extends Component {
    constructor(props) {
        super(props);
        this.state = {
            answer: ''
        };
       // this.renderSolutionText = this.renderSolutionText.bind(this);
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        fetch('http://localhost:8080/stack-api/gradle')
            .then(results => {
                return results.json();
            })
            .then(data => {
                console.log(data);
                this.setState({answer: data.body})
            })
    }


    render() {
        return (
            <div className="container2">
                <button className="btn btn-primary" onClick={this.handleClick}>
                    Stackoverflow solution
                </button>
                <div className="container1">
                    {this.state.issues}
                </div>
            </div>
        );
    }
}

export default StackApi;