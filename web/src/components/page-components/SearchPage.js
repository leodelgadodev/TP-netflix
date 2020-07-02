import React, { useEffect, useState } from 'react';
import {useLocation} from 'react-router-dom'
import { MediaService } from '../../services/MediaService';
import Header from '../shared/Header';
import MediaGrid from '../media/MediaGrid';

function useQuery() {
    return new URLSearchParams(useLocation().search);
}

export default function SearchPage(){

    const [content, setContent] = useState([]);

    const query = useQuery();
    useEffect( () => {
        MediaService.search(query.get("content")).then(res => {
            console.log(res.data.Contents)
            setContent(res.data.Contents);
        })
    }, []);
    return(
        <div className="search-page">
            <Header />
            <MediaGrid contents= {content} />
        </div>
    );
    
}

