import React from 'react';
import ReactDOM from 'react-dom';
import Modal from 'react-modal';
import ErrorMessage from './ErrorMessage';
import SuccessNotificaiton from './SuccessNotification';
import StackApi from "../StackApi/StackApi";
import { Button,Col,Row} from 'react-bootstrap';

const customStyles = {
    content : {
        top                   : '50%',
        left                  : '50%',
        right                 : 'auto',
        bottom                : 'auto',
        marginRight           : '-50%',
        transform             : 'translate(-50%, -50%)'
    }
};

Modal.setAppElement('#root');

class LoginModal extends React.Component {
    constructor() {
        super();
        this.state = {
            modalIsOpen: false,
            showError: false,
            logIn: false,
            username: '',
            password: '',
            token:''

        };

        this.openModal = this.openModal.bind(this);
        this.signIntoSee = this.signIntoSee.bind(this);
        this.afterOpenModal = this.afterOpenModal.bind(this);
        this.closeModal = this.closeModal.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.setLogin = this.setLogin.bind(this);
    }

    openModal() {
        this.setState({modalIsOpen: true});
    }

    afterOpenModal() {
        // references are now sync'd and can be accessed.
        this.subtitle.style.color = '#5bb85b';
        this.setState({showError: false})
    }

    closeModal() {
        this.setState({modalIsOpen: false});
    }

    handleChange({ target }) {
        this.setState({
            [target.name]: target.value
        });
    }

    signIntoSee(){
        this.setState({token: btoa(this.state.username+":"+this.state.password)})
        fetch("http://localhost:8080/stack-api/",{
            method: 'GET',
            headers: new Headers({
                    'Authorization': 'Basic ' + this.state.token,
                'Content-Type': 'application/x-www-form-urlencoded'
            })
        })
            .then(results => {
                return results.json();
            }).catch(function() {
        })
            .then(data => {
                if(data == null)
                {this.setState({showError: true})}
                else
                 this.setOutcome()
            }).catch(function() {
            }).then().catch(function () {
            this.setState({showError: true})
        })

    }

    setOutcome()
    {
        this.setState({logIn: true});
        this.setState({modalIsOpen: false});
    }

    setLogin()
    {
        this.setState({logIn: false});
    }

    render() {
        if( this.state.logIn === true){
            return (
                <div>
                    <Button bsStyle="danger"  onClick={this.setLogin}>Logout</Button>
                    <StackApi token={this.state.token}/>
                    <SuccessNotificaiton active = {true}/>
                </div>
            )
        }
        else
        return (
            <div>
                <Button bsStyle="success" onClick={this.openModal}>Login</Button>
                <Modal
                    isOpen={this.state.modalIsOpen}
                    onAfterOpen={this.afterOpenModal}
                    onRequestClose={this.closeModal}
                    style={customStyles}
                    contentLabel="Example Modal"
                >
                    <button type="button" className="close" aria-label="Close"onClick={this.closeModal}>
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h1 ref={subtitle => this.subtitle = subtitle}>Sign in</h1>
                    { this.state.showError && <ErrorMessage /> }
                    <p>Please enter your credentials to use the application</p>

                    <form>
                        <label htmlFor="username">Username:</label>
                        <input type="text" className="form-control" id="username" name="username" placeholder="Enter username" value ={this.state.username} onChange={this.handleChange} />
                        <label htmlFor="password">Password:</label>
                        <input type="password" className="form-control" id="password" name="password" placeholder="Enter password" value ={this.state.password} onChange={this.handleChange}/>
                    </form>
                    <hr></hr>
                    <Row className="show-grid">
                        <Col xs={6} md={4}>
                            <Button bsStyle="default" onClick={this.closeModal}>Cancel</Button>
                        </Col>
                        <Col xs={6} md={4}>
                        </Col>
                        <Col xsHidden md={4}>
                            <Button bsStyle="success" onClick={this.signIntoSee}>Sign in</Button>
                        </Col>
                    </Row>
                </Modal>
            </div>
        );
    }
}

export default LoginModal;