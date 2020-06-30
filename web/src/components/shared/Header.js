import React from 'react';
import {Link} from 'react-router-dom';
import SearchBar from './SearchBar';

export default function Header() {

    const singOut = () => {
        window.sessionStorage.accessToken = "";
    }

    return (
        <div className="header-container">
            <nav className="navbar" id="barra-navegacion">
                <div className="navbar-logo" >
                    <Link to="/">
                        <img className="logo" src="unqflix-logo.png" alt="unqflix logo"/>
                    </Link>
                </div>
                <SearchBar />
                <div className="logout-btn">
                    <Link to="/login" onClick={singOut}>
                        <img className="logout" src="enlace-muerto.svg" alt="logout"/>
                    </Link>
                </div>
                
            </nav>
        </div>
    );
}