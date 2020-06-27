import React from 'react';
import Header from '../Header';

export default function VideoPage({media}){
    return (
        <div className= "video-page">
            <Header />
            <div className="video-container">
                <iframe className="video-player" title="a" src={media.video} frameBorder="0" allowFullScreen />
            </div>
        </div>
    );
}