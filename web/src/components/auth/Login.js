import React, { useState } from 'react';
import {Link, useHistory} from 'react-router-dom';
import {AuthService} from '../../services/AuthService';
import Swal from 'sweetalert2'
import unqflix from '../../img/unqflix-logo.png'

function Login() {
    
    const [email, setEmail] = useState(null);
    const [password, setPassword] = useState(null);

    let history = useHistory();

    const restore = () => {
        setEmail(null);
        setPassword(null);
    }

    const login = (event) => {
        event.preventDefault();
        AuthService.login(email, password).then((res) => {
            AuthService.authenticate(res.headers.authentication);
            history.push("/");
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
        <div className="auth-container" id="login-container">
            <div className="modal-header">
                <img className="unqflix-logo-modal" src={unqflix} alt= "Unflix logo" id="login-logo"></img>
            </div>
            <form className="login-form">
                <div className="form-item">
                    <input type="email" className="form-control" onChange={updateEmail} placeholder="Ingrese su Email"/>
                </div>
                <div className="form-item">
                    <input type="password" className="form-control" onChange={updatePassword} placeholder="Ingrese su Contraseña"/>
                </div>
                <div className="form-item">
                    <button type="submit" className="btn btn-primary form-item" id="login" onClick={login}>Iniciar Sesión</button>
                </div>
                <div id="open-register" className="form-item">
                    <Link to="/register">
                        <span onClick={restore} className="btn-register">
                            ¿No tienes usuario? Haz click aquí para registrarse
                        </span>
                    </Link>
                </div>
            </form>
        </div>
    );
}

export default Login;