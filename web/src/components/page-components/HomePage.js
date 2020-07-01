import React from 'react';
import Header from '../shared/Header';
import MediaPage from './MediaPage';

export default function HomePage(props) {
    return (
        <div className="home-page">
            <Header/>
            <MediaPage />
        </div>
    );
}
