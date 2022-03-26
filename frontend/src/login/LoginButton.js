import React from "react"

const LoginButton = (props) => {
  return (
    <div className="button-login-9">
      <span className="span-9">{props.loginNow || "Login now"}</span>
    </div>
  )
}
export default LoginButton

<style>
.button-login-9 {
  margin-right: 0px;
  padding-bottom: 19px;
  border-radius: 5px;
  padding-left: 64px;
  min-height: 50px;
  margin-bottom: 0px;
  flex-basis: 50px;
  background-color: #04c35c;
  margin-top: 0px;
  max-width: 347px;
  padding-top: 15px;
  padding-right: 64px;
  align-self: stretch;
  margin-left: 2px;
}
.span-9 {
  font-weight: 700;
  font-size: 16px;
  line-height: 18px;
  color: #ffffff;
}
</style>