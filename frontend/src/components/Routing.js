import React, {useEffect, useContext} from 'react';
import {
        Switch,
        Route,
        useHistory
} from 'react-router-dom'; 
import Login from './Login';
import Register from './Register';
import Home from './Home';
import User from './User';
import Profile from './Profile';
import Events from './Events';
import UserContext from '../contexts/UserContext';


const Routing = () => {

    const history = useHistory();
    const { state, dispatch } = useContext(UserContext)


  useEffect(() => {

    const user = JSON.parse(localStorage.getItem("userDetails"))

    if (user) {
      dispatch({ type: "USER", payload: user })
      history.push("/")
    }
    else {
      history.push("/login")
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])


    return(
        <Switch>
          <Route path="/login">
          <Login />
          </Route>
          <Route path="/register">
          <Register />
          </Route>
          <Route path="/users">
          <User />
          </Route>
          <Route path="/events">
          <Events />
          </Route>
          <Route path="/profile">
          <Profile />
          </Route>
          <Route path="/">
            <Home />
          </Route>


        </Switch>
    )
}

export default Routing;