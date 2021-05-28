import React, { useState,useContext } from 'react';
import { Form, Input, Button, Checkbox } from 'antd';
import UserContext from '../contexts/UserContext';
import {BASE_URL} from '../Settings';
import {useHistory,Link} from 'react-router-dom';
import './css/Login.css';

const Login = () => {

    const history = useHistory();
    const {state,dispatch} = useContext(UserContext);

    const [username,setUsername] = useState("")
    const [password,setPassword] = useState("")

    const login = (event) => {

        event.preventDefault();
        console.log("Login - Submit button clicked")
        console.log("Username : " + username)
        console.log("Password : " + password)

        const body = {
            userName : username,
            password : password
        }

        const options = {
            method : 'POST',
            mode: 'cors',
             headers: {
        'Content-Type': 'application/json'
             },
            body : JSON.stringify(body)
        }

        fetch(BASE_URL + '/auth/login',options)
        .then(res => {
            if(!res.ok)
               {
                   console.log("Login failed " + res.status)
                   throw res;
               }
               return res.json();
        })
        .then(data => {
            console.log(data)
            window.localStorage.setItem('jwt',data.jwt);
            window.localStorage.setItem('userDetails',JSON.stringify(data.userDetails));
            dispatch({ type: "USER", payload: data.userDetails })
            history.push('/');
        })

    }

    return(
        <div className="login">
            <h1>Login</h1>

            <form onSubmit={login}>

            <div className="form-group">
                <label className="form-item">
                    Username :
                </label>
                <input type="text" className="form-item"
                value={username} 
                onChange={ e=>setUsername(e.target.value)}/>
            </div>

            <div className="form-group">

                <label className="form-item">
                    Password :
                </label>

                <input type="password" className="form-item"
                value = {password}
                onChange = { e=>setPassword(e.target.value)} />

            </div>

            <div className="form-group">

                <input className="form-item submit-button" type="submit"/>
            </div>

            </form>

            <Link to="/register">Register</Link>
        </div>
    )
}

export default Login;