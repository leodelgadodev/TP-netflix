import React from 'react';
import SearchBar from './SearchBar';
import {Link} from 'react-router-dom';

export default function Header(){
    return (
        <div className="header-container">
            <nav className="navbar" id="barra-navegacion">
                <div class="navbar-logo" >
                    <Link to="/">
                        <img className="logo" src="https://pbs.twimg.com/profile_images/2241566105/Logo_Q_TWITER_400x400.jpg" alt="unqflix logo"/>
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