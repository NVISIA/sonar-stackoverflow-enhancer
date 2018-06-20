import React, { Component } from 'react';
import '../App.css';

class SonarApi extends Component {
    constructor(props) {
        super(props);
        this.state = {
            issues:[],
        };
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        fetch('http://localhost:9000/api/issues/search')
            .then(results => {
                return results.json();
            })
            .then(data => {
                let issues = data.issues.map((issue) => {
                    return (
                        <p key={issue.key}>{issue.message}</p>
                    )
                })
                this.setState({issues: issues})
            })
    }

    render() {
        return (
                <div className="container2">
                    <button className ="btn btn-primary" onClick={this.handleClick}>
                        Get issues from sonar
                    </button>
                    <div className="container1">
                        {this.state.issues}
                    </div>
                </div>
        );
    }
}

export default SonarApi;