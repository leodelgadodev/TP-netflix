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

    const registerOnClick = () => {
        AuthService.register(name, email, password, imageLink, creditCard).then( res => {
            {/* eslint-disable-next-line react/prop-types */}
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
        <div className="container" id="register-container">
            <div className= "register-form">
                <div className="form-group"> 
                    <input type="email" className="form-control" onChange= {updateEmail} placeholder="Email..."/>
                </div>
                <div className="form-group"> 
                    <input type="text" className="form-control" onChange= {updateName} placeholder="Name..."/>
                </div>
                <div className="form-group"> 
                    <input type="password" className="form-control" onChange= {updatePassword} placeholder="Password..."/>
                </div>
                <div className="form-group"> 
                    <input type="url" className="form-control" onChange= {updateImageLink} placeholder="Image Link..."/>
                </div>
                <div className="form-group"> 
                    <input type="number" className="form-control" onChange= {updateCC} placeholder="Credit Card..."/>
                </div>
                <button type="submit" className="btn btn-primary" id="register" onClick={registerOnClick}>Register</button>
            </div>
            <div className="btn-register" id="close-register">
                <u onClick={restore}> <Link to="/login"> back </Link> </u>
            </div>
        </div>
    );
}