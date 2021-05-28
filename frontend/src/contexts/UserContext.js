import { createContext } from 'react';

const user = {

    id: null,
    email: null,
    firstName: null,
    lastName: null,
    userName : null,
}  

const UserContext = createContext();

export default UserContext;


