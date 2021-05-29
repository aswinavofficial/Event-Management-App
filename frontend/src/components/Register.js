import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import {BASE_URL} from '../Settings';
import './css/Register.css';


const Register = () => {

    const history = useHistory();

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
            return res.json();
        })
        .then(data => {
            console.log("Register Response : " + JSON.stringify(data));
            history.push('/login');

        })
        .catch(err => {
            console.log("Error : " + err);
        });


      }

    return(
        <div>
            <h1>Register</h1>

            <form onSubmit = {register}>

            <div className="form-group">
            <label className="form-item">First Name</label>
            <input value={firstName} className="form-item"
            onChange={e => setFirstName(e.target.value)}/>
            </div>

            <div className="form-group">
            <label className="form-item">Last Name</label>
            <input value={lastName} className="form-item"
            onChange={e=> setLastName(e.target.value)}/>
            </div>

            <div className="form-group">
            <label className="form-item">Username</label>
            <input value={userName} className="form-item"
            onChange = {e=> setUserName(e.target.value)}
            />
            </div>

            <div className="form-group">
            <label className="form-item">Email</label>
            <input value={email} className="form-item"
            onChange = {e=> setEmail(e.target.value)}
            />
            </div>

            <div className="form-group">
            <label className="form-item"> Mobile Number</label>
            <input value={mobileNumber} className="form-item" 
            onChange={e=> setMobileNumber(e.target.value)}/>
            </div>

            <div className="form-group">
            <label className="form-item">Password</label>
            <input value={password} className="form-item"
            onChange = {e=> setPassword(e.target.value)} />
            </div>

            <div className="form-group">
            <input type='submit'/>
            </div>

            </form>
        </div>
    )

}

export default Register;