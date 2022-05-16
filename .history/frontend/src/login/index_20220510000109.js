import React from "react";
import LoginForm from "./LoginForm";
import LoginButton from "./LoginButton";
import "./index.css";

import image1 from "../../../assets/image1.png";
import { Button } from "@material-ui/core";

const index = (props) => {
  return (
    <div>
      <div className="div-2">
        <img className="img-1" src={image1} />
        <div className="div-3">
          <div className="div-4">
            <div className="div-5">
              <span className="span-1">Welcome Back!</span>
              <span className="span-2">Login to your account</span>
            </div>
            <LoginForm />
            <LoginButton />
          </div>
          <Button className="span-3">Dont have an account? Join free today</span>
        </div>
      </div>
    </div>
  );
};
export default index;
