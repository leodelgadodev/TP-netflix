import React, { useEffect } from 'react';
import Header from '../shared/Header';
import MediaCarousel from '../media/MediaCarousel';
import { UserService } from '../../services/UserService';

export default function HomePage(props) {
    
    useEffect(() => {
        init();
      });
    
    const init = () => {
        UserService.addFavorite("ser_1");
        // UserService.addFavorite("mov_2");
        // UserService.addFavorite("mov_3");
        // UserService.addFavorite("mov_4");
        // UserService.addFavorite("mov_5");
        // UserService.addFavorite("mov_6");
        // UserService.addFavorite("mov_7");
        // UserService.addFavorite("mov_8");
        // UserService.addFavorite("mov_9");
        // UserService.addFavorite("mov_10");
    }

    return (
        <div className="home-page">
            <Header/>
            <h2>Favoritos</h2>
            <MediaCarousel flag="favs"/>
            <h2>Vistos Recientemente</h2>
            <MediaCarousel flag="lastSeen"/>
        </div>
    );
}
