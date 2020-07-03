import React, { useState, useEffect } from 'react';
import { UserService } from '../../services/UserService';
import MediaButton from './MediaButton';
import HorizontalScroller from 'react-horizontal-scroll-container';


export default function MediaCarousel(props) {

    const [mediaArray, setMediaArray] = useState([])
    
    useEffect(() => {
        // eslint-disable-next-line react/prop-types
        switch(props.flag) {
            case "favs": {
                getUser().then((res) => {
                    setMediaArray(res.data.favorites);
                });
            } break;
            case "lastSeen": {
                getUser().then(res => {
                    setMediaArray(res.data.lastSeen);
                })
            } break;
            case "relatedContent":{
                setMediaArray(props.relatedContent);
            } break; 
        }
    }, []);

    const getUser = () => {
        return UserService.getUser();
    }
    
    if(mediaArray === []){
        return <h2>aun no tienes {props.flag} :(</h2>
    } else {
        return(
            <div className="media-carousel">
                <HorizontalScroller>
                    {mediaArray.map (content => (
                        <MediaButton key={content.id} content={content} className="media-scroll-elem" />
                    ))}
                </HorizontalScroller>
            </div>
        );
    }
    
}