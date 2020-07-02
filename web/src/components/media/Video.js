import React, { useState, useEffect } from 'react';

export default function Video(props) {

    const {url, setUrl} = useState("");

    useEffect(() => {
        debugger; console.log(props.url);
        debugger; setUrl(props.url);
    }, [])
    
    return(
        <div className="video-container">
            <p>VIDEO FUNCIONAAAA</p>
            {/* <iframe className="video-player"  src={url.replace("watch?v=", "embed/")} frameBorder="0" allowFullScreen /> */}
        </div>
    );
    
}