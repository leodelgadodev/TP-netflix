import React from 'react';
import {Link} from 'react-router-dom';
import SearchBar from './SearchBar';

export default function Header() {


    return (
        <div className="header-container">
            <nav className="navbar" id="barra-navegacion">
                <div className="navbar-logo" >
                    <Link to="/">
                        <img className="logo" src="logounq.jpg" alt="unqflix logo"/>
                    </Link>
                </div>
                <SearchBar />
                <div className="logout-btn">
                    <Link to="/login">
                        <img className="logout" src="enlace-muerto.svg" alt="logout"/>
                    </Link>
                </div>
                
            </nav>
        </div>
    );
}