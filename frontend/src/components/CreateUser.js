import React, { useState } from 'react';
import './css/CreateUser.css';

function CreateUser({addUser,userDetails}) {

    const [firstName, setFirstName] = useState(userDetails.firstName);
    const [lastName, setLastName] = useState(userDetails.lastName);
    const [mobileNumber, setMobileNumber] = useState(userDetails.mobileNumber);
    const [email, setEmail] = useState(userDetails.email);
    const [userName, setUserName] = useState(userDetails.userName);
    const [password, setPassword] = useState(userDetails.password !== undefined?userDetails.password:"");
    const [id, setId] = useState(userDetails.id);

    const submitValue = (event) => {

        event.preventDefault();
        console.log("Submit Button Clicked");

        const user = {

            'firstName': firstName,
            'lastName': lastName,
            'mobileNumber': mobileNumber,
            'userName': userName,
            'password': password,
            'email' : email,
            'id' : id
        }

        addUser(user);
        
    }

    return (
        <div className="create-user">

            <div className="form-container">
            <form onSubmit={submitValue} className="create-user-form">

                <div className="form-group">
                <label className="form-label">
                    First Name
                </label>
                     <input type="text" value={firstName} onChange={e => setFirstName(e.target.value)} />
                
                </div>


                <div className="form-group">
                <label className="form-label">
                    Last Name
                    </label>
                      <input type="text" value={lastName} onChange={e => setLastName(e.target.value)} />
                
                </div>


                <div className="form-group">
                <label className="form-label"> 
                    Mobile Number
                    </label>
                      <input type="text" value={mobileNumber} onChange={e => setMobileNumber(e.target.value)} />
               
                </div>

                <div className="form-group">
                <label className="form-label"> 
                    Email 
                    </label>
                      <input type="text" value={email} onChange={e => setEmail(e.target.value)} />
        
                </div>


                <div className="form-group">
                <label className="form-label">
                    User Name:
                    </label>
                      <input type="text" value={userName} onChange={e => setUserName(e.target.value)} />
               
                </div>

                <div className="form-group">
                <label className="form-label">
                    Password:
                    </label>

                      <input type="text" value={password} onChange={e => setPassword(e.target.value)} />
                </div>

                <div className="form-group">
                <input type="submit" value="Submit" />
                </div>

            </form>
            </div>
        </div>
    )

}

export default CreateUser;

