import React from 'react'

export default function SerieDisplay(props){

    const media = props.media;

    if(!media){
        return <h2>404 not found</h2>
    } 
    return(
        <div>
            <h1>{media.title}</h1>
            <img src = {media.poster} alt={media.title}/>
            <p>{media.description}</p>
            <h5>Seasons</h5>
        </div>
    );
    
}
