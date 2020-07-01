import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css"; 
import "slick-carousel/slick/slick-theme.css";

export default class BannerCarousel extends React.Component {
  render() {
    const settings = {
      dots: true,
      infinite: true,
      speed: 500,
      slidesToShow: 1,
      slidesToScroll: 1
    };
    return (
      <div className="banner-carousel">
        <Slider {...settings}>
          <div className="banner">
            <h3>1</h3>
          </div>
          <div className="banner">
            <h3>2</h3>
          </div>
          <div className="banner">
            <h3>3</h3>
          </div>
          <div className="banner">
            <h3>4</h3>
          </div>
          <div className="banner">
            <h3>5</h3>
          </div>
          <div className="banner">
            <h3>6</h3>
          </div>
        </Slider>
      </div>
    );
  }
}