import React, {useState} from 'react';
import LoadingOverlay from 'react-loading-overlay';
import User from './User';
import './css/Home.css';

const Home = () =>  {

    const [isLoading, setIsLoading] = useState(false);

  const toggleIsLoading = (value) => {

    setIsLoading(value);

  };

  return(
    <LoadingOverlay class= "loading-overlay"
    active={isLoading}
    spinner
    text='Loading ...'
  >

<div className="Home">

<div className="container">
  
  <User isLoading={toggleIsLoading} /> 


</div>

</div>


</LoadingOverlay>
  )

}

export default Home;