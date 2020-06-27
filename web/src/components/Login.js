import React, { useState } from 'react';
import {Link, useHistory, useLocation} from 'react-router-dom';
import api from '../Api';

function Login(props){
    
    const [email, setEmail] = useState(null);
    const [password, setPassword] = useState(null);

    let history = useHistory();
    let location = useLocation();
    let { from } = location.state || { from: { pathname: "/" } };

    const restore = () => {
        setEmail(null);
        setPassword(null);
    }

    const login = () => {
        api.log(email, password).then((res) => {
            props.auth(res.headers.authentication);
            restore();
            history.replace(from);
        }).catch((err) => {
            console.log(err.response);
        });
    }
    
    const updatePassword = (event) => {
        setPassword(event.target.value);
    }
    const updateEmail = (event) => {
        setEmail(event.target.value);
    }

    return (
        <div className="container" id="login-container">
            <img className="unqflix-logo" src= "logounq.jpg" alt= "Unflix logo" id="login-logo"></img>
            <div className="login-form">
                <div className="form-group">
                    <label>Email</label>
                    <input type="email" className="form-control" onChange= {updateEmail}/>
                </div>
                <div className="form-group">
                    <label>Password</label>
                    <input type="password" className="form-control" onChange={updatePassword}/>
                </div>
                <button type="submit" className="btn btn-primary" id="login" onClick={login}>Login</button>
            </div>
            <div className="btn-register" id="open-register">
                <u onClick={restore}><Link to="/register">Register </Link></u>
            </div>
        </div>
    );
}

export default Login;