import React from "react";
import "LoginButton";

const LoginButton = (props) => {
  return (
    <div className="button-login-9">
      <span className="span-9">{props.loginNow || "Login now"}</span>
    </div>
  );
};
export default LoginButton;
