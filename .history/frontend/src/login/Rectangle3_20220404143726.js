import React from "react"

const Rectangle3 = (props) => {
  return (
    <div className="textfield-7">
      <span className="span-10">
        {props.johnSnowGmailCom || "John.snow@gmail.com"}
      </span>
    </div>
  )
}
export default Rectangle3

<style>
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
.span-10 {
  line-height: 16px;
  color: #2d3748;
  font-weight: 400;
  font-size: 14px;
}