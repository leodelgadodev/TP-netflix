import React from 'react';
import {Link} from 'react-router-dom';
import SearchBar from './SearchBar';

export default function Header() {

    return (
        <header className="header" id="barra-navegacion">
            <Link to="/">
                <img className="unqflix-logo" src="unqflix-logo.png" alt="unqflix logo"/>
            </Link>
            <SearchBar />
            <Link to="/login">
                {/* <img className="logout" src="enlace-muerto.svg" alt="logout"/> */}
                <button className="btn btn-primary btn-logout">Cerrar Sesión</button>
            </Link>
        </header>
    );
}