import React from 'react';
import MediaButton from './MediaButton';

export default function MediaGrid({contents}){

    if(!contents){
        return(
            <div className="media-grid">
            </div>
        );
    } else {
        return(
            <div className="container-fluid">
                <div className="row">
                    {contents.map (content => (
                        <MediaButton key={content.id} content={content} className="col" />
                        ))}
                </div>  
            </div>
        );
    }

}