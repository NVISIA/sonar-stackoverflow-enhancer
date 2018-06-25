import React, { Component } from 'react';
import { Button} from 'react-bootstrap';
import './SonarApi.css'
import SelectList from "./SelectList"
import StackApi from "../StackApi/StackApi";

class SonarApi extends Component {
    constructor(props) {
        super(props);
        this.state = {
            issues:[],
        };
        this.handleClick = this.handleClick.bind(this);
        this.handleClick()
    }

    handleClick() {
        fetch('http://localhost:9000/api/issues/search')
            .then(results => {
                return results.json();
            })
            .then(data => {
                let issues = data.issues.map((issue) => {
                    var message = (issue.message)
                    message = message.replace(/['"]+/g, '')
                    var output ={ "value": message ,"label":  message}
                    return (output )
                })
                this.setState({issues: issues});
                console.log(issues);
            })
    }

    render() {
        console.log(this.state.issues)
        return (
                    <SelectList values={this.state.issues} token = {this.props.token} />
        );
    }
}

export default SonarApi;