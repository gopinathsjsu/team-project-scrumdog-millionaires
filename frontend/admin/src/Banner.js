import React, { useState } from "react";
import "./Banner.css";
import bannerImg from "./assets/adminSplashPicture.jpg";

function Banner() {
  return (
    <div>
      <img class="banner" src={bannerImg} />
    </div>
  );
}

export default Banner;
