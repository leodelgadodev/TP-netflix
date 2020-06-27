import React from 'react';
import Login from '../auth/Login';
export default function LoginPage(props){
    return (
        <div className="login-page">
            <Login auth={props.auth}/>
        </div>
    );
}