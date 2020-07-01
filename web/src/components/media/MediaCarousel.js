import React, { useState, useEffect } from 'react';
import { UserService } from '../../services/UserService';
import { MediaService } from '../../services/MediaService';



export default function MediaCarousel(props) {

    const [mediaArray, setMediaArray] = useState([])
    
    const [fst, ...rest] = mediaArray
    
    useEffect(() => {
        // eslint-disable-next-line react/prop-types
        switch(props.flag) {
            case "favs": {
                getUser()
                .then((res) => {
                    // debugger; setMediaArray(res.data.favorites);
                    res.data.favorites.forEach((content) => {
                        mediaArray.push(getContentById(content.id))
                    });
                });
            } break;
            case "lastSeen": {
                // UserService.getUser().then((data) => {
                //     debugger; setMediaArray(data.lastSeen);
                //     debugger; console.log(data.lastSeen);
                // })
            } break; 
            default: {
                // si no envia favs o lastSeen, envia id del content
                // eslint-disable-next-line react/prop-types
                getContentById(props.flag).then((data) => {
                    debugger; setMediaArray(data.relatedContent);
                    debugger; console.log(data.relatedContent);
                })
            } break;
        }
    }, []);

    const getUser = () => {
        //del User saco los Last Seen y los Favs
        return UserService.getUser();
    }
    
    const getContentById = (id) => {
        return MediaService.getContent(id);
    }

    const verifyPoster = (content) => {
        if(content) {
            return MediaService.verifyPoster(content.poster);
        } else return "notfound.jpg"
    }

    return(
        <div id="carouselExampleControls" className="carousel slide media-carousel" data-ride="carousel">
            <div className="carousel-inner">
                <div className="carousel-item active">
                    <img className="media-poster-img" src={verifyPoster(fst)} alt="First slide"/>
                </div>
                    {rest.map (content => (
                        <div key={content.id} className="carousel-item ">
                            <img src={ verifyPoster(content) } className="media-poster-img" alt={content.title}/>
                        </div> 
                    ))}
                <a className="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span className="sr-only">Previous</span>
                </a>
                <a className="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                    <span className="sr-only">Next</span>
                </a>
            </div>
        </div>
    );
}