import React from 'react';
import {Link} from 'react-router-dom';


class SearchBar extends React.Component{
    constructor(props){
        super(props);
        this.props = props;
        this.state = {
            searchText: "",
            path: ""
        }
    }

    updateSearchText = (event) => {
        this.setState({searchText: event.target.value});
        this.setState({path: `/search?content=${this.state.searchText}`})
    }

    render(){
        return (
            <div className="seacrh-box">
                <form className="form-inline my-2 my-lg-0">
                    <input className="form-control mr-sm-2" type="search" placeholder="Buscar..." aria-label="Search" onChange={this.updateSearchText} />
                    <Link to={this.state.path}><img className="search-svg" src="buscar.svg" alt="buscar"></img> </Link>
                </form>
            </div>
        );
    }

}

export default SearchBar;
