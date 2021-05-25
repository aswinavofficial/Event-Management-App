import React, { useState } from 'react';
import {BASE_URL} from '../Settings';
const Login = () => {

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
        })

    }

    return(
        <div>
            <h1>Login</h1>

            <form onSubmit={login}>

                <label>
                    Username
                </label>
                <input type="text" 
                value={username} 
                onChange={ e=>setUsername(e.target.value)}/>

                <label>
                    Password
                </label>

                <input type="password"
                value = {password}
                onChange = { e=>setPassword(e.target.value)} />

                <input type="submit"
                  
                />

            </form>
        </div>
    )
}

export default Login;