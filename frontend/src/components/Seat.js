import React from 'react';
import './css/Seat.css';

const Seat = (props) => {

    return(
        <div>
            <button onClick={()=> props.onClick(props.seatDetails.seatNumber)}
            className={props.seatDetails.selected? "green-button": ""}
            disabled = {!props.seatDetails.available}
            >
            Seat {props.seatDetails.seatNumber}
            </button>
            
        </div>
    )
}


export default Seat;