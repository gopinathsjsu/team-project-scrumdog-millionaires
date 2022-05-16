import React from "react";
import styled from "styled-components";

const RegisterView = ({}) => {
  const LoginNowFunction = (e, name) => {
    alert(`${name} has initiated`);
  };

  return (
    <RegisterViewRoot>
      <Image1 src={"https://file.rendit.io/n/ejXZxMjHnhm0CsYvxKHi.png"} />
      <FlexColumn>
        <Text1>Welcome!</Text1>
        <Text2>Register</Text2>
        <Element5>
          <Loginform>
            <Text3 margin={"0"}>First Name</Text3>
            <FlexRow padding={"16px 19px"} />
            <Text3 margin={"0"}>Last Name</Text3>
            <FlexRow padding={"19px 19px 13px 19px"} />
          </Loginform>
          <Loginform1>
            <Text3 margin={"0px 0px 9.44px 0px"}>Password</Text3>
            <FlexRow2 />
            <Text3 margin={"0px 0px 9.44px 0px"}>Re-Enter Password</Text3>
            <FlexRow3 padding={"18.7px 19px 12.4px 19px"} />
          </Loginform1>
          <Loginform2 top={"390px"}>
            <Text3 margin={"0px 0px 9px 0px"}>Address </Text3>
            <FlexRow4 />
            <Text3 margin={"0px 0px 10px 0px"}>Pincode</Text3>
            <FlexRow3 padding={"18px 19px 13.1px 19px"} />
          </Loginform2>
          <Loginform2 top={"585px"}>
            <Text3 margin={"0px 0px 9px 0px"}>City</Text3>
            <FlexRow4 />
            <Text3 margin={"0px 0px 10px 0px"}>State</Text3>
            <FlexRow3 padding={"18px 19px 13.1px 19px"} />
          </Loginform2>
        </Element5>
        <LoginNow onClick={(e) => LoginNowFunction(e, "Registration")}>
          Register
        </LoginNow>
        <HaveAnAccountLogin2>
          Have an account?<HaveAnAccountLogin> </HaveAnAccountLogin>
          <HaveAnAccountLogin1>Login</HaveAnAccountLogin1>
        </HaveAnAccountLogin2>
      </FlexColumn>
    </RegisterViewRoot>
  );
};

export default RegisterView;

const RegisterViewRoot = styled.div`
  background-color: #ffffff;
  display: flex;
  overflow: hidden;
  flex-direction: row;
  justify-content: flex-start;
  gap: 169px;
  margin: auto;
  min-width: 1487px;
  align-items: center;
`;
const Image1 = styled.img`
  width: 744px;
  height: 1087px;
`;
const FlexColumn = styled.div`
  width: 350px;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  flex-grow: 1;
  align-items: flex-start;
  padding: 29px 0px;
`;
const Text1 = styled.div`
  font-size: 16px;
  font-family: Roboto;
  font-weight: 400;
  color: #2d3748;
  margin: 0px 0px 2px 0px;
`;
const Text2 = styled.div`
  font-size: 30px;
  font-family: Roboto;
  font-weight: 700;
  color: #1a202c;
  margin: 0px 0px 24px 0px;
`;
const Element5 = styled.div`
  width: 350px;
  height: 796px;
  align-self: center;
  position: relative;
`;
const Loginform = styled.div`
  width: 350px;
  height: 215px;
  display: flex;
  position: absolute;
  flex-direction: column;
  justify-content: flex-start;
  gap: 10px;
  align-items: center;
`;
const Loginform1 = styled.div`
  width: 347px;
  height: 211px;
  display: flex;
  position: absolute;
  top: 197px;
  left: 1px;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
`;
const FlexRow2 = styled.div`
  border-width: 1px;
  border-color: #e7e7e7;
  border-style: solid;
  width: 307px;
  height: 16px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  border-radius: 5px;
  padding: 15.69px 19px 15.41px 19px;
  margin: 0px 0px 9.77px 0px;
`;
const LoginNow = styled.button`
  display: flex;
  font-size: 16px;
  font-family: Roboto;
  font-weight: 700;
  color: #ffffff;
  width: 347px;
  height: 19px;
  background-color: #04c35c;
  flex-direction: row;
  justify-content: center;
  border-radius: 5px;
  padding: 15px 0px 16px 0px;
  margin: 0px 0px 48px 1px;
  border-width: 0px;
  box-sizing: content-box;
  cursor: pointer;
  &: hover {
    box-shadow: inset 0 0 100px 100px rgba(255, 255, 255, 0.3);
  }
`;
const HaveAnAccountLogin2 = styled.div`
  font-size: 16px;
  font-family: Roboto;
  font-weight: 400;
  color: #606060;
  align-self: center;
`;
const HaveAnAccountLogin = styled.div`
  font-size: 16px;
  font-family: Roboto;
  font-weight: 400;
  display: contents;
  white-space: pre-wrap;
`;
const HaveAnAccountLogin1 = styled.div`
  font-size: 16px;
  font-family: Roboto;
  font-weight: 400;
  color: #2b6cb0;
  display: contents;
`;
const Text3 = styled.div`
  font-size: 16px;
  font-family: Roboto;
  font-weight: 400;
  color: #4a5568;
  align-self: flex-start;
  margin: ${(props) => props.margin};
`;
const FlexRow = styled.div`
  border-width: 1px;
  border-color: #e7e7e7;
  border-style: solid;
  width: 310px;
  height: 16px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  border-radius: 5px;
  padding: ${(props) => props.padding};
`;
const FlexRow3 = styled.div`
  border-width: 1px;
  border-color: #e7e7e7;
  border-style: solid;
  width: 307px;
  height: 16px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  border-radius: 5px;
  padding: ${(props) => props.padding};
`;
const Loginform2 = styled.div`
  width: 347px;
  height: 211px;
  display: flex;
  position: absolute;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  top: ${(props) => props.top};
`;
const FlexRow4 = styled.div`
  border-width: 1px;
  border-color: #e7e7e7;
  border-style: solid;
  width: 307px;
  height: 16px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  border-radius: 5px;
  padding: 16px 19px 15.1px 19px;
  margin: 0px 0px 9.9px 0px;
`;
