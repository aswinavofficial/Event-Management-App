import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import Logout from './Logout';
import UserContext from '../contexts/UserContext';
import {FaUserFriends} from 'react-icons/fa';
import {AiOutlineUser, AiFillHome} from 'react-icons/ai';
import './css/NavBar.css'
import EventifyLogo from './images/eventify-logo.png';
function NavBar() {

    const { state, dispatch } = useContext(UserContext);


    if (state != null) {
        return (
            <div className="navbar">

                <div className="nav-brand">
                    <Link to="/">
                        <img className="eventify-logo" src={EventifyLogo} alt="EVENTIFY" />
                    </Link>
                </div>

                <div className="nav-home">

                <Link className="link" to="/">
                <AiFillHome size={33} />
                </Link>

                </div>

                <div className="nv-users">
                <Link className="link" to="/users">
                <FaUserFriends size={33} />
                </Link>

                </div>

                <div className="user-block">
                <Link className="link" to="/profile">
                <AiOutlineUser size={33} className="nav-userprofile"/>
                </Link>

                <div className="nav-logout">
                    <Logout />
                </div>

                </div>


            </div>
        )
    }
    else {
        return (

            <div className="navbar">
                <Link to="/">
                    <img className="eventify-logo" src={EventifyLogo} alt="EVENTIFY" />
                </Link>
            </div>

        )
    }

}


export default NavBar;
