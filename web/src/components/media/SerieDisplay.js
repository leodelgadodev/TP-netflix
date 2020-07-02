import React from 'react'

export default function SerieDisplay(props){

    const media = props.media;


    const TableHeader = () => {
        const rows = media.season.map((row, index) =>{ 
            return(
                <th key= {index}>
                    <td>Season {index + 1}</td>
                </th>
            )
        });
        return <thead>{rows}</thead>
    }

    const TableBody = () => {
        const rows = media.season.map((row, index) =>{
            const chapters = row.chapters.map((chap, index)=>{
                return(
                            <tr key={index}>{chap.title}</tr>
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
        <div className="media">
            <img src = {media.poster} className="mr-3" alt={media.title}/>
            <div className="media-body">
                <h1 className="mt-0">{media.title}</h1>
                <p className="mt-0">{media.description}</p>
                <h5 className="mt-0">Seasons</h5>
                <div>
                    <table className="mt-0">
                        <TableHeader />
                        <TableBody />
                    </table>
                </div>
            </div>
        </div>
    );
    
}
