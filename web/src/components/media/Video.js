import React from 'react';

export default function Video(props) {
    
    if(!props.url){
        return <h2>Not Found</h2>
    }else{
        return(
            <div className="video-container">
                <iframe className="video-player"  src={props.url.replace("watch?v=", "embed/")} frameBorder="0" allowFullScreen />
            </div>
        );
    }
    
}