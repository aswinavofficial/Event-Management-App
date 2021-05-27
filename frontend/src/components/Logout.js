import React, {useState} from 'react';
import {Redirect} from 'react-router-dom';

const Logout = () => {

    const [loggedOut, setLoggedOut] = useState(false)
  
    const logout = () => {
      localStorage.removeItem("jwt");
      localStorage.removeItem("userDetails");
      setLoggedOut(true)
  
    };
  
    if (loggedOut) {
      return <Redirect to="/login" push={true} />
    }
  
    return <button onClick={logout}>LogOut</button>;
  };


  export default Logout;