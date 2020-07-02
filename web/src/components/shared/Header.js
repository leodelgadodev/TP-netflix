import React from 'react';
import {Link} from 'react-router-dom';
import SearchBar from './SearchBar';
import unqflix from '../../img/unqflix-logo.png'

export default function Header() {

    return (
        <header className="header" id="barra-navegacion">
            <Link to="/">
                <img className="unqflix-logo" src={unqflix} alt="unqflix logo"/>
            </Link>
            <SearchBar />
            <Link to="/login">
                <button className="btn btn-primary btn-logout">Cerrar Sesión</button>
            </Link>
        </header>
    );
}