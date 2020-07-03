import React, { useState, useEffect } from "react";
import { MediaService } from "../../services/MediaService";
import notfound from '../../img/notfound.jpg'

export default function Banner(props) {

    const [poster, setPoster] = useState(""); 

    useEffect( () => {
        MediaService.verifyPoster(props.img).then(res => {
            if(res.status === 200){
                setPoster(props.img);
            } else {
                setPoster(notfound);
            }
        })
    },[])

    return(
        <div className="banner">
            <div className="relative-container">
                <a href={`media/${props.contentId}`}><img src={poster} /></a>
                <h3 className="img-text">{props.text}</h3>
            </div>
        </div>
    );
}