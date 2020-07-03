import React, { useEffect, useState } from 'react';
import { UserService } from '../../services/UserService';

export default function FavButton(props) {

    const [favMsg, setFavMsg] = useState("");

    useEffect(() => {
        UserService.getUser().then((res) => {
            if (res.data.favorites.some(content => content.id === props.mediaId
            )) {
                console.log("No esta en Favs");
                setFavMsg("Quitar de favoritos");
            } else {
                console.log("Esta en Favs");
                setFavMsg("Agregar a favoritos");
            }
        });
    }, [])

    const toggleFavorite = () => {
        UserService.addFavorite(props.mediaId);
        if (favMsg === "Quitar de favoritos") {
            setFavMsg("Agregar a favoritos");
        } else {
            setFavMsg("Quitar de favoritos");
        }
    }

    return(
        <button className="btn btn-primary form-item" onClick={() => toggleFavorite()}>
            {favMsg}
        </button>
    );
}