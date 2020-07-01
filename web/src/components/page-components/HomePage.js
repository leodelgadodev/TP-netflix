import React from 'react';
import Header from '../shared/Header';
import MediaCarousel from '../media/MediaCarousel';
import BannerCarousel from '../banner/BannerCarousel';

export default function HomePage() {

    return (
        <div className="home-page">
            <Header/>
            <BannerCarousel/>
            <section className="section-favoritos">
                <h2>Favoritos</h2>
                <MediaCarousel flag="favs"/>
            </section>
            <section className="section-lastseen">
                <h2>Vistos Recientemente</h2>
                <MediaCarousel flag="lastSeen"/>
            </section>
        </div>
    );
}
