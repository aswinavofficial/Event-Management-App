import React, { useState } from 'react';
import { useHistory,Link } from 'react-router-dom';
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
        <div className="register-container">
            <h1>Register</h1>

            <form onSubmit = {register}>

            <div className="form-group">
            <label className="form-item">First Name</label>
            <input value={firstName} className="form-item" required={true}
            onChange={e => setFirstName(e.target.value)}/>
            </div>

            <div className="form-group">
            <label className="form-item">Last Name</label>
            <input value={lastName} className="form-item" required={true}
            onChange={e=> setLastName(e.target.value)}/>
            </div>

            <div className="form-group">
            <label className="form-item">Username</label>
            <input value={userName} className="form-item" required={true}
            onChange = {e=> setUserName(e.target.value)}
            />
            </div>

            <div className="form-group">
            <label className="form-item">Email</label>
            <input input type="email" value={email} className="form-item" required={true}
            onChange = {e=> setEmail(e.target.value)}
            />
            </div>

            <div className="form-group">
            <label className="form-item"> Mobile Number</label>
            <input type="tel" value={mobileNumber} pattern="[0-9]{10}" className="form-item"  required={true}
            onChange={e=> setMobileNumber(e.target.value)}/>
            </div>

            <div className="form-group">
            <label className="form-item">Password</label>
            <input type="password" minlength="8"
             value={password} className="form-item" required={true}
            onChange = {e=> setPassword(e.target.value)} />

            </div>

            <div className="form-group">
            <input className="submit-button" type='submit'/>
            </div>

            </form>

            <Link to="/login">Login</Link>
        </div>
    )

}

export default Register;