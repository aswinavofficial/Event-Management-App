import React, { useState, useEffect } from 'react';
import UserCard from './UserCard';
import CreateUser from './CreateUser';

import './css/User.css';

function User({isLoading}) {

  const [users, setUsers] = useState([]);


  function getAllUsers() {

    isLoading(true);

    fetch('/user')
      .then(res => res.json())
      .then(data => {
        setUsers(data)
        isLoading(false);
      })
  }

  const createUser = (user) => {

    isLoading(true);

    const options = {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(user)
    }

    fetch('/user', options)
      .then(res => res.json())
      .then(data => {
        console.log(data)
        getAllUsers()
      })

  }

  const deleteUser = (id) => {

    isLoading(true);

    const options = {
      method: 'DELETE'
    }

    fetch('/user/' + id, options)
      .then(res => {
        console.log(res)
        getAllUsers()

      })

  }

  useEffect(() => {

    getAllUsers()
    
  }, []);

  return (


      <div className="user">


        <CreateUser addUser={createUser} />

        <div className="user-list">
          {
            users.map(user =>
              <UserCard key={user.id} user={user} deleteUser={deleteUser} />
            )
          }

        </div>



      </div>


  )

}

export default User;
