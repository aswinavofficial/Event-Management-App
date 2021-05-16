import React from 'react';
import './css/UserCard.css';

function UserCard(props) {

    const user = props.user;

    const deleteUser = () => {

      console.log("Delete Button Clicked :" + user.id);

      props.deleteUser(user.id);

    }

    return(
        <div className="user-card">

          <p>{user.firstName}</p>
          <p>{user.lastName}</p>
          <p>{user.email}</p>
          <p>{user.mobileNumber}</p>

          <button className="delete-button" onClick = {e => deleteUser()}>
            Delete User
          </button>

        </div>
    )


}

export default UserCard;