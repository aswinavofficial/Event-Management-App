import React,{useReducer} from 'react';
import NavBar from './components/NavBar';
import Routing from './components/Routing';
import UserContext from './contexts/UserContext';
import { reducer, initialState } from './reducers/UserReducer';
import {
  BrowserRouter as Router,
} from "react-router-dom";

import './App.css';


const App = () => {

  const [state, dispatch] = useReducer(reducer, initialState);

  return (
      <UserContext.Provider value={{ state, dispatch }}>
      <Router>
      <div className="App">
        <NavBar />
        <Routing />
      </div>
      </Router>
      </UserContext.Provider>
  );
}

export default App;
