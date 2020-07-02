import React from 'react';
import MediaButton from './MediaButton';

export default function MediaGrid({contents}){

    if(!contents){
        return(
            <div className="media-grid">
                <h4>
                    Searched content
                </h4>
            </div>
        );
    } else {
        return(
            <div className="media-grid">
                <h4>
                    Searched content
                </h4>
                <div className="media-grid-content">
                    {
                        contents.map (content => (
                            <MediaButton key={content.id} content={content} className="carousel-item" />
                        ))
                    }
                </div>
            </div>  
        );
    }

}