import React, { useEffect, useState } from 'react';
import Event from './Event';
import {BASE_URL} from '../Settings';
import './css/Events.css';
const Events = () => {

    const [events,setEvents] = useState([])

    useEffect(() => {

        console.log('Events component mounted');

        const jwt = localStorage.getItem('jwt');

        const options = {
            method : 'GET',
            mode : 'cors',
            headers : {
                'Authorization' : 'Bearer ' + jwt
            }
        }
        fetch(BASE_URL + '/event',options)
        .then(res => {
            if(!res.ok) {
                console.log('GET /events API call failed' + res.status);
                throw res;
            }
            return res.json()
        })
        .then(data => {
            console.log('GET /events API call - Success');
            console.log(JSON.stringify(data));
            setEvents(data);
        })
        .catch(err => {
            err.text()
            .then(errtext => {
                console.log(errtext)
            })
        })


    },[])

    return(
        <div className="events-container">

            {
                events.map(event => {
                  return  <Event key={event.id} event={event}/>
                })
            }

        </div>
    )

}

export default Events;