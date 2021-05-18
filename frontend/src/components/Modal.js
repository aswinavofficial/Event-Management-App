import React from 'react';
import './css/Modal.css';

function Modal({handleClose,show,children}) {

    const showHideClassName = show ? "modal display-block" : "modal display-none";

    return(
        <div className = {showHideClassName}>

            <section className="modal-main">

            <button onClick = {handleClose} className="close-button">
                Close
            </button>

            {children}

           

            </section>

        </div>
    )

}

export default Modal;