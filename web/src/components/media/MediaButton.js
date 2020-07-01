import React, { useState } from 'react';
import { MediaService } from '../../services/MediaService';

export default function MediaButton({content}){

    const [poster, setPoster] = useState("");

    const verifyPoster = () => {
        setPoster(MediaService.verifyPoster(content.poster));
        return poster;
    }

    if(!content){
        return "notfound.jpg"
    } else {
        return (
            <div key={content.id} className="carousel-item ">
                <img src={ verifyPoster(content) } className="media-poster-img" alt={content.title}/>
            </div> 
        );
    }
    
}