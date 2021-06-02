import React from 'react';
import { Link } from 'react-router-dom';
import UsersImg from './images/users.png';
import EventsImg from './images/events.png';
import QrImg from './images/qr.png';

import './css/Home.css';
import { useHistory } from 'react-router-dom';

const Home = () =>  {

  const history = useHistory();



  return(

<div className="Home">

<div className="container">

<Link className="link" to="/users">

<div className="item-box">
<img className="img-icon" src={UsersImg} alt="USERS" />

<p>Users</p>
</div>
</Link>

<Link className="link" to="/events">

<div className="item-box">
<img className="img-icon" src={EventsImg} alt="EVENTS" />
<p>Events</p>
</div>
</Link>

<Link className="link" to="/qr">

<div className="item-box">
<img className="img-icon" src={QrImg} alt="QR" />
<p>QR</p>
</div>
</Link>

</div>
</div>

  )

}

export default Home;