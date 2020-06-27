import React, { useState } from 'react';
import {Link} from 'react-router-dom';

function SearchBar(){

    const [searchText, setSearchText] = useState("");
    const [path, setPath] = useState("");

    const updateSearchText = (event) => {
        setSearchText(event.target.value);
        setPath(`/search?content=${searchText}`)
    }

    return (
        <div className="form-inline my-2 my-lg-0">
            <input className="form-control mr-sm-2" type="search" placeholder="Buscar..." aria-label="Search" onChange={updateSearchText} />
            <Link to={path}><img className="search-svg" src="buscar.svg" alt="buscar"></img> </Link>
        </div>
    );
}

export default SearchBar;
