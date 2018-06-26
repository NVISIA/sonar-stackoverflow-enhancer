import React from 'react';
import {Notification} from 'react-notification';

class SuccessNotification extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isActive: this.props.active
        }
    }

    toggleNotification() {
        this.setState({
            isActive: !this.state.isActive
        })
    }

    render() {
        return (
            <div>
                <Notification
                    isActive={this.state.isActive}
                    message="You're now logged in"
                    action="Dismiss"
                    onDismiss={this.toggleNotification.bind(this)}
                    onClick={() =>  this.setState({isActive: false })}
                />
            </div>
        );
    }
}

export default SuccessNotification;