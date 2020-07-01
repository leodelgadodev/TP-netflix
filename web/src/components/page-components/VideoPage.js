import React, { useState, useEffect } from 'react';
import Header from '../shared/Header';
import Video from '../media/Video';
import { useParams } from 'react-router-dom';
import { MediaService } from '../../services/MediaService';

export default function VideoPage(){

    const [media, setMedia] = useState(null);
    const {id} = useParams();
    useEffect(() => { 
        MediaService.getContent(id).then(res => {
            setMedia(res.data)
        }).catch(() => null)
        
    },[]);

    return (
        <div className= "video-page">
            <Header />
            <Video media={media}/>
        </div>
    );
}