import React from 'react';

export default function Video({media}){
    return(
        <div className="video-container">
            <iframe className="video-player" title={media.title} src={media.video.replace("watch?v=", "embed/")} frameBorder="0" allowFullScreen />
        </div>
    );
    
}