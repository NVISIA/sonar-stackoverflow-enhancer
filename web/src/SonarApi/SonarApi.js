import React, { Component } from 'react';
import { Button} from 'react-bootstrap';
import './SonarApi.css'

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
                    <Button bsStyle="primary" onClick={this.handleClick}>
                        Get issues from sonar
                    </Button>
                    <div className="issueslist">
                        {this.state.issues}
                    </div>
                </div>
        );
    }
}

export default SonarApi;