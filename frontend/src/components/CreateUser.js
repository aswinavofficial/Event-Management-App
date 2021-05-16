import React, { useState } from 'react';
import './css/CreateUser.css';

function CreateUser({addUser}) {

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [mobileNumber, setMobileNumber] = useState("");
    const [email, setEmail] = useState("");
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");

    const submitValue = (event) => {

        event.preventDefault();
        console.log("Submit Button Clicked");

        const user = {

            'firstName': firstName,
            'lastName': lastName,
            'mobileNumber': mobileNumber,
            'userName': userName,
            'password': password,
            'email' : email
        }

        addUser(user);
        
    }

    return (
        <div className="create-user">

            <form onSubmit={submitValue} className="create-user-form">
                <label className="form-item">
                    First Name:
                     <input type="text" value={firstName} onChange={e => setFirstName(e.target.value)} />
                </label>


                <label className="form-item">
                    Last Name:
                      <input type="text" value={lastName} onChange={e => setLastName(e.target.value)} />
                </label>


                <label className="form-item"> 
                    Mobile Number:
                      <input type="text" value={mobileNumber} onChange={e => setMobileNumber(e.target.value)} />
                </label>


                <label className="form-item"> 
                    Email :
                      <input type="text" value={email} onChange={e => setEmail(e.target.value)} />
                </label>

                <label className="form-item">
                    User Name:
                      <input type="text" value={userName} onChange={e => setUserName(e.target.value)} />
                </label>

                <label className="form-item">
                    Password:
                      <input type="text" value={password} onChange={e => setPassword(e.target.value)} />
                </label>

                <input type="submit" value="Submit" />
            </form>
        </div>
    )

}

export default CreateUser;

