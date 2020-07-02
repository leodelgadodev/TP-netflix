import React, { useState, useEffect } from 'react';
import { MediaService } from '../../services/MediaService';
import notfound from '../../img/notfound.jpg'

export default function MediaButton({content}){

    const [poster, setPoster] = useState(""); 

    useEffect( () => {
        if(content){
            MediaService.getContent(content.id).then(media => {
                MediaService.verifyPoster(media.data.poster).then(res => {
                    if(res.status == 200){
                        setPoster(media.data.poster);
                    } else {
                        setPoster(notfound);
                    }
                });
            });
        } else {
            setPoster(notfound);
        }
    },[]);

    return (
        <div className="media-button grid-item">
            <a href={`/media/${content.id}`}> 
                <img src={ poster } className="media-poster-img" alt={content.title}/> 
            </a>
        </div> 
    );
}