import React from 'react';
import {Link} from 'react-router-dom';
import SearchBar from './SearchBar';
import unqflix from '../../img/unqflix-logo.png'
import { AuthService } from '../../services/AuthService';

export default function Header() {

    const logout = () => {
        AuthService.logout();
    }

    return (
        <header className="header" id="barra-navegacion">
            <Link to="/">
                <img className="unqflix-logo" src={unqflix} alt="unqflix logo"/>
            </Link>
            <SearchBar />
            <Link to="/login">
                <button className="btn btn-primary btn-logout" onClick={logout}>Cerrar Sesión</button>
            </Link>
        </header>
    );
}