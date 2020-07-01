import React from 'react';

export default function Video(props){

    const media = props.media 
    if(!media){
        console.log(media);
        return <h2>404 not found</h2>
    } else {
        console.log(media);
        return(
            <div className="video-container">
                <iframe className="video-player" title={media.title} src={media.video.replace("watch?v=", "embed/")} frameBorder="0" allowFullScreen />
            </div>
        );
    }
    
}