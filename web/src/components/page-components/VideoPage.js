import React, { useState, useEffect } from 'react';
import Header from '../shared/Header';
import Video from '../media/Video';

export default function VideoPage(props){
    console.log(props);
    return (
        <div className= "video-page">
            <Header />
            <Video />
        </div>
    );
}