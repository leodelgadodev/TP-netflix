// eslint-disable-next-line react/no-deprecated
import React from 'react';
import Login from '../auth/Login';
export default function LoginPage(props) {
    return (
        <div className="login-page">
            {/* eslint-disable-next-line react/prop-types */}
            <Login auth={props.auth}/>
        </div>
    );
}