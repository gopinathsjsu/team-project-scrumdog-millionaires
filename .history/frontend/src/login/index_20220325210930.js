import React from "react"
import LoginForm from "./LoginForm"
import LoginButton from "./LoginButton"

import image1 from "./image1.svg"

const App = (props) => {
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
          <span className="span-3">Dont have an account? Join free today</span>
        </div>
      </div>
    </div>
  )
}
export default App

<style>
.div-2 {
	 justify-content: space-around;
	 flex-direction: row;
	 padding-bottom: 0px;
	 padding-left: 0px;
	 width: 100%;
	 height: 100%;
	 padding-top: 0px;
	 column-gap: 64px;
	 min-height: 900px;
	 align-items: end;
	 background-color: #ffffff;
	 display: flex;
	 max-width: 1440px;
	 padding-right: 64px;
}.img-1 {
	 height: 900px;
	 flex-basis: 720px;
	 width: 720px;
}
.div-3 {
	 margin-right: 0px;
	 flex-direction: column;
	 align-items: center;
	 row-gap: 64px;
	 margin-left: 0px;
	 margin-top: 0px;
	 justify-content: space-between;
	 margin-bottom: 49px;
	 display: flex;
	 flex-basis: 350px;
}.div-4 {
	 flex-basis: 372px;
	 margin-right: 0px;
	 width: 100%;
	 flex-direction: column;
	 margin-bottom: 0px;
	 margin-left: 0px;
	 margin-top: 0px;
	 justify-content: space-between;
	 align-items: start;
	 display: flex;
	 row-gap: 26px;
}.div-5 {
	 margin-right: 0px;
	 flex-direction: column;
	 margin-bottom: 0px;
	 margin-left: 0px;
	 margin-top: 0px;
	 align-items: start;
	 display: flex;
	 row-gap: 5px;
	 justify-content: flex-start;
	 flex-basis: 54px;
}.span-1 {
	 flex-basis: 19px;
	 color: #2d3748;
	 font-size: 16px;
	 line-height: 18px;
	 font-weight: 400;
}
.span-2 {
	 line-height: 35px;
	 font-size: 30px;
	 color: #1a202c;
	 font-weight: 700;
	 flex-basis: 30px;
}
.span-3 {
	 flex-basis: 19px;
	 color: #000000;
	 font-size: 16px;
	 line-height: 18px;
	 font-weight: 400;
}
</style>