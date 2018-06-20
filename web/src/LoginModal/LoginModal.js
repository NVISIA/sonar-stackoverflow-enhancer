import React from "react";
import Popup from "reactjs-popup";
import './LoginModal.css';
import { Button} from 'react-bootstrap';


export default () => (
    <Popup
        trigger={<Button bsStyle ="success" className="button"> Login </Button>}
        modal
        closeOnDocumentClick
    >
        <span> Please login :) thanks </span>
        <Button bsStyle="secondary"
            className="button"
            onClick={() => {
                console.log('modal closed ')
            }}
        >close
        </Button>
    </Popup>
);