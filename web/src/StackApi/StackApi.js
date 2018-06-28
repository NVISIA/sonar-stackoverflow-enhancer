import React, {Component} from 'react';
import {Button} from 'react-bootstrap';

class StackApi extends Component {
    constructor(props) {
        super(props);
        this.state = {
            answer: null,
            inputValue: ''
        };
        this.handleClick = this.handleClick.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        this.setState({inputValue: event.target.value});
    }

    handleClick() {
        fetch("http://localhost:8080/stack-api/"+ this.props.value.label,{
            method: 'GET',
            headers: new Headers({
                'Authorization': 'Basic ' + this.props.token,
                'Content-Type': 'application/x-www-form-urlencoded',
                'Access-Control-Allow-Origin': '*'
            })
        })
            .then(results => {
                return results.json();
            }).catch(function() {
            })
            .then(data => {
                this.setState({answer: data.body})
            }).catch(function() {
            })

    }


    render() {
        return (
            <div className="container2">
                {/*<input id="userInput" placeholder="Enter custom issue" value={this.state.inputValue}  onChange={this.handleChange}/>*/}
                <br />
                <Button bsStyle="primary" onClick={this.handleClick}>
                    Stackoverflow solution
                </Button>
                <br/>
                <table align="center">
                    <tbody>
                        <tr>
                             <td dangerouslySetInnerHTML={{__html: this.state.answer}} />
                        </tr>
                    </tbody>
                </table>
            </div>
        );
    }
}

export default StackApi;