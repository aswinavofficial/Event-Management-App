import React,{useContext} from 'react';
import QRCode from "react-qr-code";

import './css/Event.css';
import {BASE_URL} from '../Settings';
import UserContext from '../contexts/UserContext';

const Event = ({event}) => {

    const { state, dispatch } = useContext(UserContext);

    const bookNow = () => {

        const bookingDetails = {

            'quantity': 1,
            'userId': state.id,
            "paymentDetails": {
                "amount": event.price,
                "cardNumber": "4876079513907292",
                "cvv": "524",
                "expiryDate": "2023-08-13T08:20:10.811Z",
                "payerName": state.firstName,
                "type": "DEBIT-CARD"
              },
        }
 
        const options = {
          method: 'POST',
          mode: 'cors',
          headers: {
            'Content-Type': 'application/json',
            'Authorization' : 'Bearer ' + window.localStorage.getItem('jwt')
          },
          body: JSON.stringify(bookingDetails)
        }
    
        fetch(BASE_URL + '/event/book/' + event.id, options)
          .then(res => 
            
            {
              if(!res.ok){
                console.log("POST /user failed " + res.status)
                throw res}
                  
              return res.json()
    
            })
          .then(data => {
            console.log(data)
            //getAllUsers()
          })
          .catch(err => {
            console.error(err) 
    
            err.text()
            .then(errMessage => {
    
              console.log(errMessage)
              //setErrorMessage(errMessage)
    
            })
            
          });
    
      }

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
                <QRCode value={event.id} size={100} />
                <br/>
                <button className="book-now" onClick={bookNow}>
                    Book Now
                </button>

        </div>
    )
}

export default Event;