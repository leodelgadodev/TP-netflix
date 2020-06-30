import React, { useState } from 'react';
import {Link, useHistory, useLocation} from 'react-router-dom';
import {AuthService} from '../../services/AuthService';
import Swal from 'sweetalert2'

function Login() {
    
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
        AuthService.login(email, password).then((res) => {
            {/* eslint-disable-next-line react/prop-types */}
            AuthService.authenticate(res.headers.authentication);
            restore();
            console.log(res.headers.authentication);
            history.replace(from);
        }).catch((err) => {
            if(err.response.status === 404){
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "Email o contraseña inválidos.",
                })
            } else if (err.response.status === 400) {
                Swal.fire({
                    icon: 'warning',
                    title: 'Oops...',
                    text: "Por favor, complete todos los campos.",
                })
            }
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
            <img className="unqflix-logo" src= "unqflix-logo.png" alt= "Unflix logo" id="login-logo"></img>
            <div className="login-form">
                <div className="form-group">
                    <label>Email</label>
                    <input type="email" className="form-control" onChange={updateEmail}/>
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