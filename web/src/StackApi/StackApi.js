import React, { Component } from 'react';
import { Button} from 'react-bootstrap';

class StackApi extends Component {
    constructor(props) {
        super(props);
        this.state = {
            answer: null
        };
       // this.renderSolutionText = this.renderSolutionText.bind(this);
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        fetch('http://localhost:8080/stack-api/gradle')
            .then(results => {
                return results.json();
            }).catch(function() {
            console.log("error");
            })
            .then(data => {
                console.log(data);
                this.setState({answer: data.body})
            }).catch(function() {
            console.log("error")
            })

    }


    render() {
        return (
            <div className="container2">
                <Button bsStyle="primary" onClick={this.handleClick}>
                    Stackoverflow solution
                </Button>
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