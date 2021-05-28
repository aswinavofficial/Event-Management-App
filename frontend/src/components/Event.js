import React from 'react';
import './css/Event.css';

const Event = ({event}) => {

    return(
        <div className="event-container">

                {event.name} <br/>
                {event.description}  <br/>
                {event.registrationEndDateTime}  <br/>
                {event.startDateTime}  <br/>
                {event.endDateTime}  <br/>
                {event.venue}  <br/>
                {event.capacity}  <br/>
                {event.price}  <br/>
                {event.status}  <br/>
                {event.public}   <br/>
                {event.online}  <br/>

        </div>
    )
}

export default Event;