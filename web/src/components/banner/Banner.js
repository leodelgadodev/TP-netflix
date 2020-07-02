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
            <h3>{props.text}</h3>
            <img src={poster} />
        </div>
    );
}