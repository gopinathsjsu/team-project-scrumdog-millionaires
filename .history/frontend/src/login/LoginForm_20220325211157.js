import React from "react"

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
.div-6 {
  width: 100%;
  flex-direction: column;
  max-width: 350px;
  font-weight: 400;
  color: #4a5568;
  justify-content: space-between;
  line-height: 16px;
  align-items: start;
  display: flex;
  row-gap: 11px;
  min-height: 215px;
  flex-basis: 215px;
  font-size: 14px;
}
.span-4 {
  font-size: 16px;
  line-height: 18px;
  flex-basis: 17px;
}
.textfield-7 {
  flex-basis: 50px;
  max-width: 350px;
  padding-top: 17px;
  border-right-style: solid;
  align-self: stretch;
  border-right-color: #e8e8e8;
  border-radius: 5px;
  padding-left: 20px;
  padding-right: 64px;
  border-left-color: #e8e8e8;
  border-bottom-color: #e8e8e8;
  border-bottom-style: solid;
  min-height: 50px;
  padding-bottom: 17px;
  border-right-width: 1px;
  border-left-style: solid;
  border-top-width: 1px;
  border-top-color: #e8e8e8;
  border-top-style: solid;
  border-bottom-width: 1px;
  border-left-width: 1px;
}
.span-5 {
  color: #2d3748;
}
.span-6 {
  flex-basis: 14px;
  font-size: 16px;
  line-height: 18px;
}
.textfield-password-8 {
  flex-basis: 50px;
  padding-top: 20px;
  max-width: 350px;
  border-right-style: solid;
  align-self: stretch;
  border-right-color: #e8e8e8;
  border-radius: 5px;
  padding-left: 20px;
  padding-right: 64px;
  border-left-color: #e8e8e8;
  border-bottom-color: #e8e8e8;
  padding-bottom: 14px;
  min-height: 50px;
  border-bottom-style: solid;
  border-right-width: 1px;
  border-left-style: solid;
  border-top-width: 1px;
  border-top-color: #e8e8e8;
  border-top-style: solid;
  border-bottom-width: 1px;
  border-left-width: 1px;
}
.span-7 {
  color: #000000;
}
.span-8 {
  margin-right: 0px;
  color: #2c5282;
  margin-bottom: 0px;
  margin-left: 64px;
  margin-top: 0px;
  flex-basis: 16px;
}
</style>