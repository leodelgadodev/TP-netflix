import React, { useState, useEffect } from 'react';
import Header from '../shared/Header';
import Video from '../media/Video';
import { MediaService } from '../../services/MediaService';
import { useParams } from 'react-router-dom';

export default function VideoPage(props){

    const {url, setUrl} = useState("");

    const {contentId, seasonId, chapterId} = useParams();

    useEffect(() => {
        // TODO getContentById, sacar el video y pasarselo al Video
        MediaService.getContent(contentId).then((res) => {
            if(res.data.id.includes("mov")) {
                this.setUrl(res.data.video);
            } else {
                // Ir sacando el video del chapter del season (que deberian haber llegado por props)
                setUrl(
                res.data.id.season
                .find((season) => season === seasonId)
                .chapter.find((chapter) => chapter === chapterId).video
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