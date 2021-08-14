import React,{useState,useEffect} from 'react';
import Seat from './Seat';

const EventBooking = () => {

    let [seatList,setSeatList] = useState([])

    const selectedSeats = seatList.filter(seat => {
        if(seat.selected)
            return true
        else 
            return false
    })

    useEffect( () => {

        let sampleData = [ {
            seatNumber : 1,
            selected   : false,
            premium  : false,
            available : true
        },
        {
            seatNumber : 2,
            selected   : true,
            premium  : false,
            available : false
        },
        {
            seatNumber : 3,
            selected   : false,
            premium  : false,
            available : true
        },
        {
            seatNumber : 4,
            selected   : false,
            premium  : false,
            available : false
        },
        {
            seatNumber : 5,
            selected   : false,
            premium  : false,
            available : true
        }]

            setSeatList(sampleData)

    },[]
    )
    

    function seatSelected(seatNumber) {

        console.log("Seat selected : " + seatNumber)


        setSeatList([...seatList].map(
            seat => {
                if(seat.seatNumber === seatNumber) {
                    return {
                        ...seat,
                        selected : seat.selected ? false:true
                    }
                }
                else return seat;
            }
        ))
    }

    return(
        <div>
           Event Booking Component 

           {
               seatList.map(seat => {
                 return  <Seat seatDetails={seat} 
                               onClick= {seatSelected}
                               key={seat.seatNumber}/>
               })
           }

           Selected Seats : {
               selectedSeats.map(seat => {
                   return `${seat.seatNumber},`
               })
           }
        </div>
    )

}

export default EventBooking;