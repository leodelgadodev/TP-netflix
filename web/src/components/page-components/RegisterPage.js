import React from 'react';
import Register from '../auth/Register';
export default function RegisterPage(props) {
    
    return (
        <div className="register-page">
            {/* eslint-disable-next-line react/prop-types */}
            <Register auth={props.auth} />
        </div>
    );
}