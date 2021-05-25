import React, { useState } from 'react';
import NavBar from './components/NavBar';
import User from './components/User';
import Login from './components/Login';
import Register from './components/Register';
import LoadingOverlay from 'react-loading-overlay';

import './App.css';



function App() {

  const [isLoading, setIsLoading] = useState(false);

  const toggleIsLoading = (value) => {

    setIsLoading(value);

  };

  return (
    <LoadingOverlay class= "loading-overlay"
      active={isLoading}
      spinner
      text='Loading ...'
    >
      <div className="App">


        <NavBar />

        <div className="container">
        { /**  <User isLoading={toggleIsLoading} />  */ }

       
        </div>

        <Login />

        <Register />


      </div>
    </LoadingOverlay>

  );
}

export default App;
