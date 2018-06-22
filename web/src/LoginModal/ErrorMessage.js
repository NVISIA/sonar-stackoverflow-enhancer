import React from "react";

const pStyle = {
    color: '#ff2828',
    fontSize: '15px',
    textAlign: 'center'
};

class ErrorMessage extends React.Component
{
    render(){
    return (
        <div id="results" className="search-results">
            <p style = {pStyle}>Credentials were incorrect, please try again.</p>
        </div>
    )}
}

export default ErrorMessage;