import React from "react";
import "LoginForm.css";

const LoginForm = (props) => {
  return (
    <div className="div-6">
      <span className="span-4">{props.email || "Email"}</span>
      <div className="textfield-7">
        <span className="span-5">
          {props.johnSnowGmailCom || "John.snow@gmail.com"}
        </span>
      </div>
      <span className="span-6">{props.password || "Password"}</span>
      <div className="textfield-password-8">
        <span className="span-7">
          {props.b64NameKioqkioqkioq || "*********"}
        </span>
      </div>
      <span className="span-8">
        {props.forgotPassword || "Forgot password?"}
      </span>
    </div>
  )
}
export default LoginForm

<style>

</style>