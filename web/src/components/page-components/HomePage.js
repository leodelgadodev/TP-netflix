import React from 'react';
import { AuthService } from '../../services/AuthService';
import { Redirect } from 'react-router-dom';
export default function HomePage(props) {
        // return (
        //     <div className="home-page">
        //         {props.token}
        //     </div>
        // );
    if (AuthService.isAuthenticated(props.token)) {
        return (
            <Redirect to={{
                pathname: "/login"
            }}/>
        );
    } else {
        return (
            <div className="home-page">
                {props.token}
            </div>
        );
    }
}