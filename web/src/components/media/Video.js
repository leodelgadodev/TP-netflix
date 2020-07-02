import React from 'react';

export default function Video(props){
    console.log(props);
    const {url} = props.location.state
    
    return(
        <div className="video-container">
            <iframe className="video-player"  src={url.replace("watch?v=", "embed/")} frameBorder="0" allowFullScreen />
        </div>
    );
    
}