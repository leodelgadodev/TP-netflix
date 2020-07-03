import React from 'react';
import Header from '../shared/Header';
import MediaCarousel from '../media/MediaCarousel';
import BannerCarousel from '../banner/BannerCarousel';
import { Link } from 'react-router-dom';

export default function HomePage() {

    return (
        <div className="home-page">
            <Header />
            <BannerCarousel/>

            <section>
                <h2>Favoritos</h2>
                <MediaCarousel flag="favs"/>
            </section>
            
            <section>
                <h2>Vistos Recientemente</h2>
                <MediaCarousel flag="lastSeen"/>
            </section>

            <section>
                <div className="flex justify-center">
                    <Link to="/all">
                        <button type="submit" className="btn btn-primary form-item">
                            Ver el catálogo completo
                        </button>
                    </Link>
                </div>
            </section>
        </div>
    );
}
