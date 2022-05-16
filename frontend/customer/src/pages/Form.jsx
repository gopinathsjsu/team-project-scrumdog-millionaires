import React, { useState } from 'react';
import './Form.css';
import FormCopy from './FormCopy';
import FormSignup from './FormSignup';
import FormSuccess from './FormSuccess';
import { registerUser } from "../controllers/user";

const Form = () => {
  const [isSubmitted, setIsSubmitted] = useState(false);

  const [firstName, setfirstName] = useState("");
  const [lastName, setlastName] = useState("");
  const [email, setemail] = useState("");
  const [password, setpassword] = useState("");


  function submitForm(values) {
    console.log("values: ", values);
    console.log(values.password)
    if(values.password){
      let data = {};
      data.first_name = values.firstName;
      data.last_name = values.lastName;
      data.email = values.email;
      data.password = values.password;
      data.personaType = "cu";
      registerUser(data).then(res => {
        if(res.status === 200) {
          setIsSubmitted(true);
          setTimeout(() => {
            window.location.href = "/login"
          }, 3000)
        }
      })
    }
   
  }
  return (
    <>
      <div className='form-container'>
        <div className='form-content-left'>
        </div>
        {!isSubmitted ? (
          <FormSignup submitForm={submitForm} />
        ) : (
          <FormCopy />
        )}
      </div>
    </>
  );
};

export default Form;
