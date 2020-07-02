import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css"; 
import "slick-carousel/slick/slick-theme.css";
import Banner from "./Banner";

export default class BannerCarousel extends React.Component {
  render() {
    const settings = {
      dots: true,
      infinite: true,
      speed: 500,
      slidesToShow: 1,
      slidesToScroll: 1
    };
    // TODO Deberia cargarle por props a los banners el src de cada imagen
    return (
      <div className="banner-carousel">
        <Slider {...settings}>
          <Banner text="1"/>
          <Banner text="2"/>
          <Banner text="3"/>
          <Banner text="4"/>
          <Banner text="5"/>
        </Slider>
      </div>
    );
  }
}