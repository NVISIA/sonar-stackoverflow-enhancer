import React, { Component } from 'react';
import { Button} from 'react-bootstrap';

class StackApi extends Component {
    constructor(props) {
        super(props);
        this.state = {
            answer: null,
            inputValue: ''
        };
       // this.renderSolutionText = this.renderSolutionText.bind(this);
        this.handleClick = this.handleClick.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        this.setState({inputValue: event.target.value});
    }

    handleClick() {
        fetch("http://localhost:8080/stack-api/"+ this.state.inputValue,{
            method: 'GET',
            headers: new Headers({
                'Authorization': 'Basic ' + this.props.token,
                'Content-Type': 'application/x-www-form-urlencoded'
            })
        })
            .then(results => {
                return results.json();
            }).catch(function() {
            console.log("error");
            })
            .then(data => {
                console.log(data);
                console.log(this.state.inputValue)
                this.setState({answer: data.body})
            }).catch(function() {
            console.log("error")
            })

    }


    render() {
        return (
            <div className="container2">
                <input id="userInput" placeholder="Enter custom issue" value={this.state.inputValue}  onChange={this.handleChange}/>
                <br></br>
                <Button bsStyle="primary" onClick={this.handleClick}>
                    Stackoverflow solution
                </Button>
            <br></br>
                <table>
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