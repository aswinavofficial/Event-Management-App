import React,{useState} from 'react';
import Modal from './Modal';
import CreateUser from './CreateUser';
import './css/UserCard.css';

function UserCard(props) {

    const user = props.user;


    const [showEditModal,setShowEditModal] = useState(false);

    const deleteUser = () => {

      console.log("Delete Button Clicked :" + user.id);

      props.deleteUser(user.id);

    }

    const editUser = (user) => {
      
      console.log("Edit Submit: " + user.id);
      props.editUser(user);
      hideModal();

    }


    const hideModal = () => {

      setShowEditModal(false);

    }

    const showModal = () =>  {

      setShowEditModal(true);
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

          <Modal show={showEditModal} handleClose = {hideModal}>

          <CreateUser addUser={editUser} userDetails = {user}/>

          </Modal>
          <button onClick = {showModal}>Edit</button>

        </div>
    )


}

export default UserCard;