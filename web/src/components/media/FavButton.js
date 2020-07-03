import React, { useEffect, useState } from 'react';
import { UserService } from '../../services/UserService';

export default function FavButton(props) {

    const [favMsg, setFavMsg] = useState("");

    useEffect(() => {
        UserService.getUser().then((res) => {
            debugger; if (res.data.favorites.some(content => content.id == props.mediaId
            )) {
                console.log("No esta en Favs");
                debugger; setFavMsg("Quitar de favoritos");
            } else {
                console.log("Esta en Favs");
                debugger; setFavMsg("Agregar a favoritos");
            }
        });
    }, [])

    return(
        <button className="btn btn-primary form-item" onClick={() => UserService.addFavorite(props.mediaId)}>
            {favMsg}
        </button>
    );
}