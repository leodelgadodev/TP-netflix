import React from 'react';
import Header from '../shared/Header';
import Video from '../media/Video';
import { useParams } from 'react-router-dom';
import { MediaService } from '../../services/MediaService';

export default function VideoPage(){

    const {id} = useParams();
    MediaService.getContent(id).then(res => {
        return (
            <div className= "video-page">
                <Header />
                <Video media={res.data}/>
            </div>
        );
    }).catch(err => {
        return(<h1> 404 NOT FOUND</h1>)
    });
}