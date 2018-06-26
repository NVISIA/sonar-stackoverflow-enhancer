import React, {Component} from 'react';
import logo from '../NVISIA-Logo.png';
import {MenuItem, Nav, Navbar, NavDropdown, NavItem} from 'react-bootstrap';

class NavBar extends Component {
    constructor(props) {
        super(props);
        this.handleClick = this.handleClick.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        this.setState({inputValue: event.target.value});
    }

    handleClick() {
    }


    render() {
        return (
            <Navbar style={{marginBottom: "0"}} inverse collapseOnSelect>
                <Navbar.Header>
                    <Navbar.Toggle />
                </Navbar.Header>
                <Navbar.Collapse>
                    <Nav>
                        <NavItem eventKey={1} href="http://www.nvisia.com/">
                            <img src={logo} className="App-logo" alt="logo" style={{width: "50%", height: "50%"}} />
                        </NavItem>
                        <NavItem eventKey={2} href="https://github.com/NVISIA/sonar-stackoverflow-enhancer">
                            GitHub
                        </NavItem>
                        <NavDropdown eventKey={3} title="Sources" id="basic-nav-dropdown">
                            <MenuItem eventKey={3.1} href="https://api.stackexchange.com/docs">StackExchange</MenuItem>
                            <MenuItem eventKey={3.2} href="https://reactjs.org/">React</MenuItem>
                            <MenuItem eventKey={3.3} href="https://www.sonarqube.org/">sonarqube</MenuItem>
                            <MenuItem divider />
                            <MenuItem eventKey={3.3}>Separated link</MenuItem>
                        </NavDropdown>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
        );
    }
}

export default NavBar;