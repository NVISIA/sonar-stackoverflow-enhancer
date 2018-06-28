import React, {Component} from 'react';
import './SonarApi.css'
import SelectList from "./SelectList"

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
        fetch('http://localhost:8080/sonar-api',{
            method: 'GET',
            headers: new Headers({
                'Content-Type': 'application/x-www-form-urlencoded'
            })
        }).catch()
            .then(results => {
                return results.json();
            })
            .then(data => {
                let issues = data.map((issue) => {
                    // make sure message doesn't contain ' or ''
                    let message = (issue.message).replace(/['"]+/g, '\'');
                    message = message.replace(/['"]+/g, '');
                    const output ={ "value": message ,"label":  message};
                    return (output );
                });
                this.setState({issues: issues});
            })
    }

    render() {
        return (
                    <SelectList values={this.state.issues} token = {this.props.token} />
        );
    }
}

export default SonarApi;