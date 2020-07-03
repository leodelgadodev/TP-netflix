import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom';
import MediaCarousel from './MediaCarousel';
import { MediaService } from '../../services/MediaService';
import notfound from '../../img/notfound.jpg'
import FavButton from './FavButton';

export default function SerieDisplay(props){

    const media = props.media;
    const [poster, setPoster] = useState("");

    useEffect( () => {
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
    });
    const TableHeader = () => {
        const rows = media.season.map((row, index) =>{ 
            return(
                <th  key= {index}>
                    <td className="row-season">Season {index + 1}</td>
                </th>
            )
        });
        return <thead>{rows}</thead>
    }

    const TableBody = () => {
        const rows = media.season.map((row, index) =>{
            const chapters = row.chapters.map((chap, index)=>{
                return(
                    <tr key={index}>
                        <Link to={{ pathname: `/media/${media.id}/${row.id}/${chap.id}/video` }}>
                            {chap.title}
                        </Link>
                    </tr>
                )
            });
            return (
                    <td key= {index}>{chapters}</td>
            );
        });
        return <tbody>{rows}</tbody>
    }


    if(!media){
        return <h2>404 not found</h2>
    } 
    return(
        <div>
            <div className="media">
                <img src = {poster} className="mr-3" alt={media.title}/>
                <div className="media-body">
                    <h1 className="mt-0">{media.title}</h1>
                    <p className="mt-0">{media.description}</p>

                    <FavButton mediaId={media.id} />

                    <h5 className="mt-0">Temporadas</h5>

                    <table className="table table-sm table-dark media-table">
                        <TableHeader />
                        <TableBody />
                    </table>
                </div>
            </div>
            <div>
                <h2>Contenido Relacionado</h2>
                <MediaCarousel flag="relatedContent" relatedContent={media.relatedContent}/>
            </div>
        </div>
    );
    
}
