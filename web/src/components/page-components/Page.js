import React from 'react';
import { Redirect } from 'react-router-dom';
import { AuthService } from "../../services/AuthService";

export default function Page(props) {
    // no uses este componente nacho
    if (!AuthService.isAuthenticated(props.token)) {
        console.log("no hay token :(")
        return (
            <Redirect to={{
                pathname: "/login"
            }}/>
        );
    } else {
        return (
            props.component
        );
    }
}