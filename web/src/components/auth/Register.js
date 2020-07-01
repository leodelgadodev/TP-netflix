import React, { useState } from 'react';
import {Link, useLocation, useHistory} from 'react-router-dom' 
import {AuthService} from '../../services/AuthService';
import Swal from 'sweetalert2'

export default function Register() {
    
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");
    const [imageLink, setImageLink] = useState("");
    const [creditCard, setCreditCard] = useState("");


    let history = useHistory();
    let location = useLocation();
    let { from } = location.state || { from: { pathname: "/" } };

    const restore = () => {
        setCreditCard("");
        setEmail("");
        setName("");
        setImageLink("");
        setPassword("");
    }

    const registerOnClick = (event) => {
        event.preventDefault();
        AuthService.register(name, email, password, imageLink, creditCard).then( res => {
            AuthService.authenticate(res.headers.authentication);
            restore();
            history.replace(from);
        }).catch(err => {
            if(err.response.status === 409){
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: err.response.data.title,
                })
            } else if (err.response.status === 400) {
                Swal.fire({
                    icon: 'warning',
                    title: 'Oops...',
                    text: "El usuario ya existe!",
                })
            }
        });
    }

    const updatePassword = (event) => {
        setPassword(event.target.value)
    }
    const updateName = (event) => {
        setName(event.target.value)
    }
    
    const updateImageLink = (event) => {
        setImageLink(event.target.value)
    }
    const updateEmail = (event) => {
        setEmail(event.target.value)
    }
    const updateCC = (event) => {
        setCreditCard(event.target.value)
    }
    

    return(
        <div className="auth-container" id="register-container">
            <div className="modal-header">
                <img className="unqflix-logo-modal" src= "unqflix-logo.png" alt= "Unflix logo" id="login-logo"></img>
            </div>
            <form className= "register-form">

                <div className="form-item"> 
                    <input type="email" className="form-control" onChange= {updateEmail} placeholder="Email..."/>
                </div>

                <div className="form-item"> 
                    <input type="text" className="form-control" onChange= {updateName} placeholder="Nombre..."/>
                </div>

                <div className="form-item"> 
                    <input type="password" className="form-control" onChange= {updatePassword} placeholder="Contraseña..."/>
                </div>

                <div className="form-item"> 
                    <input type="url" className="form-control" onChange= {updateImageLink} placeholder="Image Link..."/>
                </div>

                <div className="form-item"> 
                    <input type="number" className="form-control" onChange= {updateCC} placeholder="Tarjeta de Crédito..."/>
                </div>

                <div className="form-item">
                    <button type="submit" className="btn btn-primary" id="register" onClick={registerOnClick}>Registrarse</button>
                </div>

                <div id="open-register" className="form-item">
                    <Link to="/login">
                        <span onClick={restore} className="btn-register">
                            Volver
                        </span>
                    </Link>
                </div>
            </form>
        </div>
    );
}