import React, { useState, useEffect } from 'react';
import { MediaService } from '../../services/MediaService';
import { Link } from 'react-router-dom';

export default function MediaButton({content}){

    const [poster, setPoster] = useState(""); 

    useEffect( () => {
        if(content){
            MediaService.getContent(content.id).then(media => {
                MediaService.verifyPoster(media.data.poster).then(res => {
                    if(res.status == 200){
                        setPoster(media.data.poster);
                    } else {
                        setPoster("notfound.jpg");
                    }
                });
            });
        } else {
            setPoster("notfound.jpg");
        }
    })

    if(!content){
        return "notfound.jpg"
    } else {
        return (
            <div className="media-button">
                <Link to={`/media/${content.id}`}> 
                    <img src={ poster } className="media-poster-img" alt={content.title}/> 
                </Link>
            </div> 
        );
    }
    
}