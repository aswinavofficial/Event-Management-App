import React, { useState, useEffect } from 'react';
import UserCard from './UserCard';
import CreateUser from './CreateUser';
import Modal from './Modal';
import {BASE_URL} from '../Settings';


import './css/User.css';

function User() {

  const [users, setUsers] = useState([]);
  const [showEditModal, setShowEditModal] = useState(false);
  const [errorMessage,setErrorMessage] = useState("")


  function getAllUsers() {

    const options = {
      method : 'GET',
      mode   : 'cors',
      headers : {
        'Authorization' : 'Bearer ' + window.localStorage.getItem('jwt')
      }
    }

    fetch(BASE_URL + '/user', options)
    .then(res => 
        
      {
        if(!res.ok){
          console.log("GET /user failed " + res.status)
          throw res}
            
        return res.json()

      })
      .then(data => {
        setUsers(data)
      })
      .catch(err => {
        console.error(err) 

        err.text()
        .then(errMessage => {

          console.log(errMessage)
          setErrorMessage(errMessage)

        })
        
      });
  }

  const createUser = (user) => {

    hideModal();
    const options = {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
        'Authorization' : 'Bearer ' + window.localStorage.getItem('jwt')
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

        })
        
      });

  }

  const deleteUser = (id) => {


    const options = {
      method: 'DELETE',
      headers : {
        'Authorization' : 'Bearer ' + window.localStorage.getItem('jwt')
      }
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

    const options = {
      method: 'PUT',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
        'Authorization' : 'Bearer ' + window.localStorage.getItem('jwt')
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

    let isMounted = true;               

    const options = {
      method : 'GET',
      mode   : 'cors',
      headers : {
        'Authorization' : 'Bearer ' + window.localStorage.getItem('jwt')
      }


  }

    fetch(BASE_URL + '/user',options)
    .then(res => 
        
      {
        if(!res.ok){
          console.log("GET /user failed " + res.status)
          throw res}
            
        return res.json()

      })
      .then(data => {

        if(isMounted) {
          setUsers(data)
      }
      })
      .catch(err => {
        console.error(err) 

      /** 
        err.text()
        .then(errMessage => {

          console.log(errMessage)
          if(isMounted) {
            setErrorMessage(errMessage)
          }
          isLoading(false);

        }) */

        
        
      });

    return () => { isMounted = false }; 

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
