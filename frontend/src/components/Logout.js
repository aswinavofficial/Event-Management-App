import React, {useContext} from 'react';
import {Redirect, useHistory} from 'react-router-dom';
import UserContext from '../contexts/UserContext';
import {AiOutlineLogout} from 'react-icons/ai';
import './css/Logout.css';

const Logout = () => {

    const {state,dispatch} = useContext(UserContext);

    const history = useHistory();
  
    const logout = () => {
      localStorage.removeItem("jwt");
      localStorage.removeItem("userDetails");
      dispatch({ type: "CLEAR"})
      history.push('/login');
    };
  
    if (state ==null) {
      return <Redirect to="/login" push={true} />
    }
   else {
    // return <button className="logout-button" onClick={logout}>Logout</button>;
    return <AiOutlineLogout size={33} className="logout-icon" onClick={logout}/>
   }
  };


  export default Logout;