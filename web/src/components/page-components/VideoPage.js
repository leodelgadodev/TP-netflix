import React, { useState, useEffect } from 'react';
import Header from '../shared/Header';
import Video from '../media/Video';
import { MediaService } from '../../services/MediaService';
import { useParams } from 'react-router-dom';

export default function VideoPage(props){

    const [url, setUrl] = useState("");

    const {contentId, seasonId, chapterId} = useParams();

    useEffect(() => {
        MediaService.getContent(contentId).then((res) => {
            if(res.data.id.includes("mov")) {
                setUrl(res.data.video);
            } else {
                setUrl(
                res.data.season
                .find((season) => season.id === seasonId)
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