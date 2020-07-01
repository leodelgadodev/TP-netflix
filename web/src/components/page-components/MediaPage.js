import React, { useEffect, useState } from 'react';
import MovieDisplay from '../media/MovieDisplay';
import SerieDisplay from '../media/SerieDisplay';
import {MediaService} from '../../services/MediaService';
import { useParams } from 'react-router-dom';

export default function MediaPage() {
    
    const [content, setContent] = useState(null);
    const {contentId} = useParams();
    
    useEffect(()=> {
        MediaService.getContent(contentId).then( res => {
            setContent(res.data);
        }).catch( () => null);
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