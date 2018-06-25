import React from 'react';
import SuccessNotificaiton from './SuccessNotification';
import { Button} from 'react-bootstrap';
import LoginModal from "./LoginModal";

class LoginManager extends React.Component {
    constructor() {
        super();
        this.state = {
            logIn: false,
            token:'temp'
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleModalChange = this.handleModalChange.bind(this);
    }

    handleChange({ target }) {
        this.setState({
            [target.name]: target.value
        });
    }

    handleModalChange(tokenId, value)
    {
        this.setState({[tokenId]: value})
    }

    render() {
        if( this.state.logIn === true){
            return (
                <div>
                    <Button bsStyle="danger">Logout</Button>
                    <SuccessNotificaiton active = {true}/>
                </div>
            )
        }
        else
            return (
                <div>
                    <LoginModal/>
                </div>
            );
    }
}

export default LoginManager;