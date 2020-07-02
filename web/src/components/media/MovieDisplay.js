import React from 'react'
import { Link } from 'react-router-dom';
import MediaCarousel from './MediaCarousel';

export default function MovieDisplay(props) {
    const media = props.media
    console.log(media);

    if(!media){
        return <h2>404 not found</h2>
    } else {
        console.log(props.relatedContent);
        return(
            <div>
                <div className="media">
                    <img src = {media.poster} className="mr-3" alt={media.title}/>
                    <div className="media-body">
                        <h5 className="mt-0">{media.title}</h5>
                        <p className="mt-0">{media.description}</p>
                        <button className="btn btn-primary form-item"><Link to={{pathname: '/video', state:{url:props.video}}}>Play</Link></button>
                    </div>
                </div>
                <div>
                    <MediaCarousel flag="relatedContent" relatedContent={media.relatedContent}/>
                </div>
            </div>
            
        )
    }
    
    
}
