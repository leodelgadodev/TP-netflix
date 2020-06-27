import React from 'react';
import Header from '../Header';
import { Redirect } from 'react-router-dom';

export default function VideoPage({media}){
    if (token === "") {
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
                <iframe className="video-player" title={media.title} src={media.video} frameBorder="0" allowFullScreen />
            </div>
        </div>
    );
}