import React from 'react';
import ReactDOM from 'react-dom';
import Modal from 'react-modal';
import { Button,Col} from 'react-bootstrap';

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
            modalIsOpen: false
        };

        this.openModal = this.openModal.bind(this);
        this.afterOpenModal = this.afterOpenModal.bind(this);
        this.closeModal = this.closeModal.bind(this);
    }

    openModal() {
        this.setState({modalIsOpen: true});
    }

    afterOpenModal() {
        // references are now sync'd and can be accessed.
        this.subtitle.style.color = '#5bb85b';
    }

    closeModal() {
        this.setState({modalIsOpen: false});
    }

    signIntoSee()
    {
        // TODO: make the api post call
        // TODO: check if the sign in was a success
        this.setState({modalIsOpen: false});
    }

    render() {
        return (
            <div>
                <Button bsStyle="success" onClick={this.openModal}>Open Modal</Button>
                <Modal
                    isOpen={this.state.modalIsOpen}
                    onAfterOpen={this.afterOpenModal}
                    onRequestClose={this.closeModal}
                    style={customStyles}
                    contentLabel="Example Modal"
                >

                    <h1 ref={subtitle => this.subtitle = subtitle}>Sign in</h1>
                    <div>I am a modal</div>

                    <div className="form-group">
                        <label htmlFor="usr">Username:</label>
                        <input type="text" className="form-control" id="usr"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="pwd">Password:</label>
                        <input type="password" className="form-control" id="pwd"/>
                    </div>

                    <Col xs={6} md={6}>
                        <Button bsStyle="default" onClick={this.closeModal}>Cancel</Button>
                    </Col>
                    <Col xs={6} md={6}>
                        <Button bsStyle="success" onClick={this.signIntoSee}>Sign in</Button>
                    </Col>
                </Modal>
            </div>
        );
    }
}

export default LoginModal;