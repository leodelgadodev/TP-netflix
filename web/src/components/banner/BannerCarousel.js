import React, { useEffect, useState } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css"; 
import "slick-carousel/slick/slick-theme.css";
import Banner from "./Banner";
import { MediaService } from "../../services/MediaService";

export default function BannerCarousel() {

  const [banners, setBanners] = useState([])
  useEffect( () => {
    MediaService.getAllBanners().then( res => {
      Promise.all(res.data.Banners.map(banner => {
        return MediaService.getContent(banner.id)
      })).then( contents => {
        setBanners(contents.map(content => {
          return content.data
        }))
      })
    })
  }, [])
  
  
    // TODO Deberia cargarle por props a los banners el src de cada imagen
  if(banners === []) {
      return <h1>Banners Not Found</h1>
  } else {
    return (
      <div className="banner-carousel">
        <Slider dots= {true} infinite={true} speed={500} slidesToShow={1} slidesToScroll={1}>
          {banners.map( banner => (
            <Banner key={banner.id} img={banner.poster} text={banner.title} contentId={banner.id}/>
          ))}
        </Slider>
      </div>
    );
  }
}