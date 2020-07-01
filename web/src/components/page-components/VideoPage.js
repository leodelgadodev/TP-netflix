import React, { useState, useEffect } from 'react';
import Header from '../shared/Header';
import Video from '../media/Video';
import { useParams } from 'react-router-dom';
import { MediaService } from '../../services/MediaService';

export default function VideoPage(props){

    return (
        <div className= "video-page">
            <Header />
            <Video media={props.media}/>
        </div>
    );
}