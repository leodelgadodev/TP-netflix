import React from 'react';
import Header from '../Header';

export default function VideoPage({media}){
    return (
        <div className= "video-page">
            <Header />
            <div className="video-container">
                <iframe className="video-player" src="" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen />
            </div>
        </div>
    );
}