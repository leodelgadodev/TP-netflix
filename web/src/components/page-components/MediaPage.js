import React, { useEffect, useState } from 'react';
import MovieDisplay from '../media/MovieDisplay';
import {MediaService} from '../../services/MediaService';
import { useParams } from 'react-router-dom';

export default function MediaPage(props) {
    
    const [movie, setMovie] = useState(null);
    const {id} = useParams();

    
    useEffect(()=>{MediaService.getContent(id).then(res=>{setMovie(res.data);console.log(res)})},[])
    
    return (
        <div className="home-page">
            <MovieDisplay movie={movie} />
        </div>
    );
}