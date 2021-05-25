import React, { useState, useEffect } from 'react';
import UserCard from './UserCard';
import CreateUser from './CreateUser';
import Modal from './Modal';
import {BASE_URL} from '../Settings';


import './css/User.css';

function User({ isLoading }) {

  const [users, setUsers] = useState([]);
  const [showEditModal, setShowEditModal] = useState(false);
  const [errorMessage,setErrorMessage] = useState("")


  function getAllUsers() {

    isLoading(true);

    fetch(BASE_URL + '/user')
    .then(res => 
        
      {
        if(!res.ok){
          console.log("GET /user failed " + res.status)
          throw res}
            
        return res.json()

      })
      .then(data => {
        setUsers(data)
        isLoading(false);
      })
      .catch(err => {
        console.error(err) 

        err.text()
        .then(errMessage => {

          console.log(errMessage)
          setErrorMessage(errMessage)
          isLoading(false);

        })
        
      });
  }

  const createUser = (user) => {

    hideModal();
    isLoading(true);

    const options = {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(user)
    }

    fetch(BASE_URL + '/user', options)
      .then(res => 
        
        {
          if(!res.ok){
            console.log("POST /user failed " + res.status)
            throw res}
              
          return res.json()

        })
      .then(data => {
        console.log(data)
        getAllUsers()
      })
      .catch(err => {
        console.error(err) 

        err.text()
        .then(errMessage => {

          console.log(errMessage)
          setErrorMessage(errMessage)
          isLoading(false);

        })
        
      });

  }

  const deleteUser = (id) => {

    isLoading(true);

    const options = {
      method: 'DELETE'
    }

    fetch(BASE_URL + '/user/' + id, options)
      .then(res => {
        console.log(res)
        getAllUsers()

      })
      .catch(err => console.error(err));

  }

  const editUser = (user) => {

    hideModal();
    isLoading(true);

    const options = {
      method: 'PUT',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(user)
    }

    fetch(BASE_URL + '/user/'+ user.id, options)
      .then(res => res.json())
      .then(data => {
        console.log(data)
        getAllUsers()
      })
      .catch(err => console.error(err));


  }

  useEffect(() => {

    getAllUsers()

  }, []);

  const intialUser = {

    'firstName': '',
    'lastName': '',
    'mobileNumber': '',
    'userName': '',
    'password': '',
    'email': '',
    'id': undefined
  }

  const hideModal = () => {

    setShowEditModal(false);

  }

  const showModal = () => {

    setShowEditModal(true);
  }

  const addUser = () => {
      
    console.log("Add button clicked  ");
    showModal()

  }

  return (


    <div className="user">

      <button onClick={addUser}>
        Add User
      </button>

      <Modal show={showEditModal} handleClose={hideModal}>

        <CreateUser addUser={createUser} userDetails={intialUser} />
      </Modal>

      {/* <p value>
      {errorMessage}
      </p> */}


      <div className="user-list">
        {
          users.map(user =>
            <UserCard key={user.id} user={user} deleteUser={deleteUser} editUser={editUser} />
          )
        }

      </div>



    </div>


  )

}

export default User;
