import React, {useEffect,useState}from 'react'
import { Link } from 'react-router-dom';
import MediaCarousel from './MediaCarousel';
import { MediaService } from '../../services/MediaService';
import notfound from '../../img/notfound.jpg'

export default function MovieDisplay(props) {
    const media = props.media

    const [poster, setPoster] = useState(""); 

    useEffect( () => {
        console.log(props.media);
        if(media){
            MediaService.verifyPoster(media.poster).then(res => {
                console.log(res);
                if(res.status == 200){
                    setPoster(media.poster);
                } else {
                    setPoster(notfound);
                }
            })
        }
    },[])

    if(!media){
        return <h2>404 not found</h2>
    } else {
        return(
            <div>
                <div className="media">
                    <img src = {poster} className="mr-3" alt={media.title}/>
                    <div className="media-body">
                        <h5 className="mt-0">{media.title}</h5>
                        <p className="mt-0">{media.description}</p>
                        <button className="btn btn-primary form-item">
                            <Link to={{ pathname: `/media/${props.media.id}/video` }}>
                                Reproducir
                            </Link>
                        </button>
                    </div>
                </div>
                <div>
                    <MediaCarousel flag="relatedContent" relatedContent={media.relatedContent}/>
                </div>
            </div>
            
        )
    }
    
    
}
