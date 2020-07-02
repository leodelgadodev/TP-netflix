import React, { useState, useEffect } from 'react';
import { UserService } from '../../services/UserService';
import { MediaService } from '../../services/MediaService';
import MediaButton from './MediaButton';
import HorizontalScroller from 'react-horizontal-scroll-container';


export default function MediaCarousel(props) {

    const [mediaArray, setMediaArray] = useState([])
    
    useEffect(() => {
        // eslint-disable-next-line react/prop-types
        switch(props.flag) {
            case "favs": {
                
                getUser().then((res) => {
                    // debugger; setMediaArray(res.data.favorites);
                    // res.data.favorites.forEach((content) => {
                    //     getContentById(content.id).then( (res) => {
                    //         const contents = mediaArray.concat([res.data])
                    //         setMediaArray(contents)
                    //     })
                    // });
                    setMediaArray(res.data.favorites);
                });
            } break;
            case "lastSeen": {
                getUser().then(res => {
                    setMediaArray(res.data.lastSeen);
                })
                // UserService.getUser().then((data) => {
                //     debugger; setMediaArray(data.lastSeen);
                //     debugger; console.log(data.lastSeen);
                // })
            } break; 
            default: {
                // si no envia favs o lastSeen, envia id del content
                // eslint-disable-next-line react/prop-types
                getContentById(props.flag).then((data) => {
                    setMediaArray(data.relatedContent);
                    console.log(data.relatedContent);
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

    const [fst, ...rest] = mediaArray

    if(mediaArray == []){
        return <h2>aun no tienes {props.flag} :(</h2>
    } else {
        return(
            // <div id="carouselExampleControls" className="carousel slide media-carousel" data-ride="carousel">
            //     <div className="carousel-inner">
            //         <MediaButton content={fst} className="carousel-item active" />
            //         {/* <div className="carousel-item active">
            //             <img className="media-poster-img" src={verifyPoster(fst)} alt="First slide"/>
            //         </div> */}
            //             {rest.map (content => (
            //                 <MediaButton key={content.id} content={content} className="carousel-item" />
            //             ))}
            //         <a className="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
            //             <span className="carousel-control-prev-icon" aria-hidden="true"></span>
            //             <span className="sr-only">Previous</span>
            //         </a>
            //         <a className="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
            //             <span className="carousel-control-next-icon" aria-hidden="true"></span>
            //             <span className="sr-only">Next</span>
            //         </a>
            //     </div>
            // </div>
            <div>
        <HorizontalScroller>
            <div className="media-scroll-elem">1</div>
            <div className="media-scroll-elem">2</div>
            <div className="media-scroll-elem">3</div>
            <div className="media-scroll-elem">4</div>
            <div className="media-scroll-elem">5</div>
            <div className="media-scroll-elem">6</div>
            <div className="media-scroll-elem">7</div>
            <div className="media-scroll-elem">8</div>
            <div className="media-scroll-elem">1</div>
            <div className="media-scroll-elem">2</div>
            <div className="media-scroll-elem">3</div>
            <div className="media-scroll-elem">4</div>
            <div className="media-scroll-elem">5</div>
            <div className="media-scroll-elem">6</div>
            <div className="media-scroll-elem">7</div>
            <div className="media-scroll-elem">8</div>
            <div className="media-scroll-elem">1</div>
            <div className="media-scroll-elem">2</div>
            <div className="media-scroll-elem">3</div>
            <div className="media-scroll-elem">4</div>
            <div className="media-scroll-elem">5</div>
            <div className="media-scroll-elem">6</div>
            <div className="media-scroll-elem">7</div>
            <div className="media-scroll-elem">8</div>
        </HorizontalScroller>
        </div>
        );
    }

    
}