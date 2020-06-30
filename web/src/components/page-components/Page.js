import React from 'react';
import { Redirect } from 'react-router-dom';
import { AuthService } from "../../services/AuthService";

export default function Page(props) {
    const Component = props.component
    const token = AuthService.token
    if (token) {
        console.log("no hay token :(")
        return (
            
            <Redirect to={{
                pathname: "/login",
            }}/>
        );
    } else {
        return (
            <Component token={token} />
        );
    }
}