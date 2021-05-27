import React, {useEffect} from 'react';
import {
        Switch,
        Route,
        useHistory
} from 'react-router-dom'; 
import Login from './Login';
import Register from './Register';
import Home from './Home';


const Routing = () => {

    const history = useHistory()

  useEffect(() => {

    const user = JSON.parse(localStorage.getItem("userDetails"))

    if (user) {
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
          <Route path="/">
            <Home />
          </Route>

        </Switch>
    )
}

export default Routing;