import React from 'react'

export default function SerieDisplay(props){

    const media = props.media;

    if(!media){
        return <h2>404 not found</h2>
    } 
    return(
        <div className="media">
            <img src = {media.poster} className="mr-3" alt={media.title}/>
                <div className="media-body">
                <h1 className="mt-0">{media.title}</h1>
                <p className="mt-0">{media.description}</p>
                <h5 className="mt-0">Seasons</h5>
            </div>
            
        </div>
    );
    
}
