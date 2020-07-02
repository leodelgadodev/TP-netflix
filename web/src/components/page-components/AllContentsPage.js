import React, { useEffect, useState } from 'react';
import Header from '../shared/Header';
import MediaGrid from '../media/MediaGrid';
import { MediaService } from '../../services/MediaService';

export default function AllContentsPage() {

    const [content, setContent] = useState([]);

    useEffect( () => {
        MediaService.getAllContents().then(res => {
            setContent(res.data.content);
        })
    }, []);

    return(
        <div className="search-page">
            <Header />
            <h2>
                Catálogo completo
            </h2>
            <MediaGrid contents= {content} />
        </div>
    );
}