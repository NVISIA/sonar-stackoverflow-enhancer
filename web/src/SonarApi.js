import React, { Component } from 'react';
import './App.css';

class SonarApi extends Component {
    constructor(props) {
        super(props);
        this.state = {
            issues:[],
        };
    }
    componentDidMount(){
        fetch('http://localhost:9000/api/issues/search')
            .then(results => {
                return results.json();
            })
            .then(data => {
                let issues = data.issues.map((issue) =>
                {
                    return(
                        <p>{issue.message}</p>
                    )
                })
                this.setState({issues: issues})
            })
    }
    render() {
        return (
                <div className="container2">
                    <div className="container1">
                        {this.state.issues}
                    </div>
                </div>
        );
    }
}

export default SonarApi;