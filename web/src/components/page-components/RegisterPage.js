import React from 'react';
import Register from '../Register';
export default function RegisterPage(props){

    const error = (message) => {
        return (
            <div className="alert alert-danger" role="alert">
                <h1>hola</h1>
            </div>
        );
    }

    return (
        <div className="register-page">
            <Register auth={props.auth} error={error}/>
        </div>
    );
}