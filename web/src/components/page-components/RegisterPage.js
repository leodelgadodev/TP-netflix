import React from 'react';
import Register from '../auth/Register';
export default function RegisterPage(props){


    return (
        <div className="register-page">
            <Register auth={props.auth} />
        </div>
    );
}