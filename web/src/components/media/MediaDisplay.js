import React, { useEffect, useState } from 'react';
import MovieDisplay from './MovieDisplay';
import SerieDisplay from '../media/SerieDisplay';
import {MediaService} from '../../services/MediaService';
import { useParams } from 'react-router-dom';
import { UserService } from '../../services/UserService';


export default function MediaDisplay(){

    const [content, setContent] = useState(null);
    const {contentId} = useParams();
    
    useEffect(()=> {
        MediaService.getContent(contentId).then( res => {
            setContent(res.data);
            UserService.addLastSeen(contentId);
        });
    },[])

    if(contentId.includes("mov")){
        return (  
            <div className="media-page">
                <MovieDisplay media={content} />
            </div>
            
        );
    } else {
        return (
            <div className="media-page">
                <SerieDisplay media={content} />
            </div>
        );
    }

}
