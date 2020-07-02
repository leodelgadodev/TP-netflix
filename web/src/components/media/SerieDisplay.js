import React from 'react'
import { Link } from 'react-router-dom';
import MediaCarousel from './MediaCarousel';

export default function SerieDisplay(props){

    const media = props.media;


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
                            <tr key={index} ><Link to={{pathname: '/video', state:{url:props.video}}}>{chap.title}</Link> </tr>
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
                <img src = {media.poster} className="mr-3" alt={media.title}/>
                <div className="media-body">
                    <h1 className="mt-0">{media.title}</h1>
                    <p className="mt-0">{media.description}</p>
                    <h5 className="mt-0">Seasons</h5>
                    <div>
                        <table class="table table-sm table-dark media-table">
                            <TableHeader />
                            <TableBody />
                        </table>
                    </div>
                </div>
            </div>
            <div>
                <MediaCarousel flag="relatedContent" relatedContent={media.relatedContent}/>
            </div>
        </div>
    );
    
}
