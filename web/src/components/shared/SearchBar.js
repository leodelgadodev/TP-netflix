import React, { useState } from 'react';

function SearchBar(){

    const [searchText, setSearchText] = useState("");

    const updateSearchText = (event) => {
        setSearchText(event.target.value);
    }

    return (
        <div className="form-inline my-2 my-lg-0">
            <input className="form-control mr-sm-2" type="search" placeholder="Buscar..." aria-label="Search" value={searchText} onChange={updateSearchText} />
            <a href={`/search?content=${searchText}`}><img className="search-svg" src="buscar.svg" alt="buscar"></img> </a>
        </div>
    );
}

export default SearchBar;
