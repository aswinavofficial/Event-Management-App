import React, { useState } from 'react';
import QrReader from 'react-qr-reader'
 

const QRScanner = () => {

    const [result,setResult] = useState("");


    const handleError = (err) => {
        console.error(err);
    }
    
    const handleScan = (data) => {

        if(data) {
            setResult(data)
        }

    }

    return(
        <div>

        <QrReader
          delay={300}
          onError={err => handleError(err)}
          onScan={ data => handleScan(data)}
          style={{ width: '100%' }}
        />

        <p> {result}</p>
        </div>
    )

}

export default QRScanner;