import React from 'react'
import Video from './Video'

export default function MovieDisplay(props) {
    const media = props.media
    console.log(media);

    if(!media){
        return <h2>404 not found</h2>
    } 
    
    return(
        <div className="media">
            <img src = {media.poster} className="mr-3" alt={media.title}/>
            <div className="media-body">
                <h5 className="mt-0">{media.title}</h5>
                <p>{media.description}</p>
                <button >Play</button>
            </div>
        </div>
    )
    
}
