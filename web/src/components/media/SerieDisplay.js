import React, { useState, useEffect } from 'react';
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
                if(res.status === 200){
                    setPoster(media.poster);
                } else {
                    setPoster(notfound);
                }
            })
        }
    });

    // const TableHeader = () => {
    //     const rows = media.season.map((row, index) =>{ 
    //         return(
    //             <th  key= {index}>
    //                 <td className="row-season">Season {index + 1}</td>
    //             </th>
    //         )
    //     });
    //     return <thead>{rows}</thead>
    // }

    // const TableBody = () => {
    //     const rows = media.season.map((row, index) =>{
    //         const chapters = row.chapters.map((chap, index)=>{
    //             return(
    //                 <tr key={index}>
    //                     <Link to={{ pathname: `/media/${media.id}/${row.id}/${chap.id}/video` }}>
    //                         {chap.title}
    //                     </Link>
    //                 </tr>
    //             )
    //         });
    //         return (
    //                 <td key= {index}>{chapters}</td>
    //         );
    //     });
    //     return <tbody>{rows}</tbody>
    // }

    const SeasonTab = () => {
        const rows = [];
        rows.push(media.season.map((row, index) =>{
            const chapters = row.chapters.map((chap, index)=>{
                return(
                    <span key={index}>
                        <Link to={{ pathname: `/media/${media.id}/${row.id}/${chap.id}/video` }}>
                            {chap.title}
                        </Link>
                    </span>
                )
            });
            const id = "nav-tab-" + index
            return (
                // poner {index} adentro de " " no funciona y se renderiza como nav-tab-{index} D:
                <div key={index} className="tab-pane fade" id={id} role="tabpanel" aria-labelledby={id}>{chapters}</div>
            );
        }));
        return(
            <div className="tab-content" id="nav-tabContent">
                {rows}
            </div>
        );
    }

    const SeasonTabHeader = () => {
        const rows = media.season.map((row, index) => { 
            const href = "#nav-tab-" + index
            return(
                // poner {index} adentro de " " no funciona y se renderiza como nav-tab-{index} D:
                <a key={index} className="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href={href} role="tab" aria-controls={href} aria-selected="false">
                    {row.title}
                </a>
            )
        });
        return (
            <nav>
                <div className="nav nav-tabs" id="nav-tab" role="tablist">
                    {rows}
                </div>
            </nav>
        );
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

                    {/* <Tabs>
                        <TabList>
                        <Tab>Title 1</Tab>
                        <Tab>Title 2</Tab>
                        </TabList>

                        <TabPanel>
                        <h2>Any content 1</h2>
                        </TabPanel>
                        <TabPanel>
                        <h2>Any content 2</h2>
                        </TabPanel>
                    </Tabs> */}
                    {/* <table className="table table-sm table-dark media-table"> */}
                        {/* <TableHeader />
                        <TableBody /> */}
                        <SeasonTabHeader/>
                        <SeasonTab/>
                    {/* </table> */}
                </div>
            </div>
            <div>
                <h2>Contenido Relacionado</h2>
                <MediaCarousel flag="relatedContent" relatedContent={media.relatedContent}/>
            </div>
        </div>
    );
    
}
