import React,{useContext} from 'react';
import { Redirect } from 'react-router-dom';
import UserContext from '../contexts/UserContext';

const Profile = () => {

    const { state, dispatch } = useContext(UserContext);

    if(state !=null) {
    return(
        <div>
            <h2>{state.firstName}</h2>
            <h2>{state.lastName}</h2>
            <h2>{state.userName}</h2>
            <h2>{state.email}</h2>
            <h2>{state.mobileNumber}</h2>
            <h2>{state.id}</h2>

            <div className="user-details">

            </div>
        </div>
    )
    }
    else {
        return(
            <Redirect to="/login" push={true}/>
        )
    }

}

export default Profile;