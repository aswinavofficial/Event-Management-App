import React, { useState } from 'react';
import NavBar from './components/NavBar';
import User from './components/User';
import LoadingOverlay from 'react-loading-overlay';

import './App.css';



function App() {

  const [isLoading, setIsLoading] = useState(false);

  const toggleIsLoading = (value) => {

    setIsLoading(value);

  };

  return (
    <LoadingOverlay
      active={isLoading}
      spinner
      text='Loading ...'
    >
      <div className="App">


        <NavBar />

        <div className="container">
          <User isLoading={toggleIsLoading} />

        </div>



      </div>
    </LoadingOverlay>

  );
}

export default App;
