import React, { useEffect, useState } from 'react';
import MovieDisplay from '../media/MovieDisplay';
import {MediaService} from '../../services/MediaService';

export default function MediaPage(props) {
    
    const [movie, setMovie] = useState(null);
    
    useEffect(()=>{MediaService.getContent("mov_1").then(res=>{setMovie(res.data);console.log(res)})},[])
    
    return (
        <div className="home-page">
            <MovieDisplay movie={movie} />
        </div>
    );
}