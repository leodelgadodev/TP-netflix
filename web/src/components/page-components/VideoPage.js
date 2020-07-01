import React from 'react';
import Header from '../Header';
import { Redirect } from 'react-router-dom';
import { AuthService } from '../../services/AuthService';

export default function VideoPage(props){
    if (AuthService.isAuthenticated(props.token)) {
        console.log("no hay token :(")
        return (
            <Redirect to={{
                pathname: "/login"
            }}/>
        );
    }
    return (
        <div className= "video-page">
            <Header />
            <div className="video-container">
                <iframe className="video-player" title={props.media.title} src={props.media.video} frameBorder="0" allowFullScreen />
            </div>
        </div>
    );
}