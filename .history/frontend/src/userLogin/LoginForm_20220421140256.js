import React from "react";
import "LoginForm.css";

const LoginForm = () => {
  return (
    <div className="div-6">
      <span className="span-4">{"Email"}</span>
      <div className="textfield-7">
        <span className="span-5">
          {"Enter User Email here..."}
        </span>
      </div>
      <span className="span-6">{"Password"}</span>
      <div className="textfield-password-8">
        <span className="span-7">
          {"Enter here..."}
        </span>
      </div>
      <span className="span-8">
        {props.forgotPassword || "Forgot password?"}
      </span>
    </div>
  );
};
export default LoginForm;
