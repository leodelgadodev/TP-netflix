import React, { useState, useEffect } from "react";
import { MediaService } from "../../services/MediaService";

export default function Banner(props) {

    const [poster, setPoster] = useState(""); 

    useEffect( () => {
        MediaService.verifyPoster(props.img).then(res => {
            if(res.status == 200){
                setPoster(props.img);
            } else {
                setPoster("notfound.jpg");
            }
        })
    })

    return(
        <div className="banner">
            <div className="relative-container">
                <img src={poster} />
                <h3 className="img-text">{props.text}</h3>
            </div>
        </div>
    );
}