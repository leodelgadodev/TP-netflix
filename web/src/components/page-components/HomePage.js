import React from 'react';
import { Redirect } from 'react-router-dom';
export default function LoginPage({token}){
    if (token === "") {
        console.log("no hay token :(")
        return (
            <Redirect to={{
                pathname: "/login"
            }}/>
        );
    } else {
        return (
            <div className="home-page">
                {token}
            </div>
        );
    }
    
}