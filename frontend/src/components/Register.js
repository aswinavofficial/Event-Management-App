import React, { useState } from 'react';
import {BASE_URL} from '../Settings';


const Register = () => {

    /** 
    {
        "email": "string",
        "firstName": "string",
        "id": "string",
        "lastName": "string",
        "mobileNumber": "string",
        "password": "string",
        "userName": "string"
      }  */

      const [email,setEmail] = useState("");
      const [firstName,setFirstName] = useState("");
      const [lastName,setLastName] = useState("");
      const [mobileNumber,setMobileNumber] = useState("");
      const [password,setPassword] = useState("");
      const [userName,setUserName] = useState("");

      const register = (event) => {

        event.preventDefault();
        console.log("Register - submit button clicked");

        const user = {
            email,
            firstName,
            lastName,
            mobileNumber,
            password,
            userName
        }

        const options = {
            method : 'POST',
            mode : 'cors',
            headers : {
                'Content-Type' : 'application/json'
            },
            body : JSON.stringify(user)
        }

        fetch(BASE_URL + '/auth/register',options)
        .then(res => {
            if(!res.ok) {
                console.log('Register failed ' + res.status)
                throw res;
            }
        })
        .then(data => {
            console.log("Register Response : " + JSON.stringify(data));

        })
        .catch(err => {
            console.log("Error : " + err);
        });


      }

      /** 
    {
        "email": "string",
        "firstName": "string",
        "id": "string",
        "lastName": "string",
        "mobileNumber": "string",
        "password": "string",
        "userName": "string"
      }  */

    return(
        <div>
            <h1>Register</h1>

            <form onSubmit = {register}>

            <label>First Name</label>
            <input value={firstName}
            onChange={e => setFirstName(e.target.value)}/>

            <label>Last Name</label>
            <input value={lastName}
            onChange={e=> setLastName(e.target.value)}/>

            <label>Username</label>
            <input value={userName}
            onChange = {e=> setUserName(e.target.value)}
            />

            <label>Email</label>
            <input value={email}
            onChange = {e=> setEmail(e.target.value)}
            />

            <label> Mobile Number</label>
            <input value={mobileNumber} 
            onChange={e=> setMobileNumber(e.target.value)}/>

            <label>Password</label>
            <input value={password}
            onChange = {e=> setPassword(e.target.value)} />

            <input type='submit'/>

            </form>
        </div>
    )

}

export default Register;