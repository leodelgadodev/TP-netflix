import React, { useState, useEffect } from 'react';
import Header from '../shared/Header';
import Video from '../media/Video';
import { MediaService } from '../../services/MediaService';
import { useParams } from 'react-router-dom';

export default function VideoPage(props){

    const [url, setUrl] = useState("");

    const {contentId, seasonId, chapterId} = useParams();

    useEffect(() => {
        // TODO getContentById, sacar el video y pasarselo al Video
        MediaService.getContent(contentId).then((res) => {
            console.log(res.data.video);
            if(res.data.id.includes("mov")) {
                setUrl(res.data.video);
            } else {
                console.log(res.data);
                // Ir sacando el video del chapter del season (que deberian haber llegado por props)
                setUrl(
                res.data.season
                .find((season) => season.id == seasonId)
                .chapters.find((chapter) => chapter.id === chapterId).video
                );
            }
        });
    }, [])

    return (
        <div className= "video-page">
            <Header />
            <Video url={url}/>
        </div>
    );
}