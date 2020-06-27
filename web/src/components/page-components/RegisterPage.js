import React from 'react';
import Register from '../Register';
import ErrorAlert from '../ErrorAlert';
export default function RegisterPage(props){

    const error = (message) => {
        console.log(message)
        return <div><ErrorAlert message={message}></ErrorAlert></div>

    }

    return (
        <div className="register-page">
            <Register auth={props.auth} error={error}/>
        </div>
    );
}