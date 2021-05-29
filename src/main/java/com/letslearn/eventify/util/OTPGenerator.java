package com.letslearn.eventify.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OTPGenerator {
	
	Logger log = LoggerFactory.getLogger(OTPGenerator.class);

	
	
	public String random(int size) {

        StringBuilder generatedToken = new StringBuilder();
        try {
        	
            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");

            for (int i = 0; i < size; i++) {
                generatedToken.append(number.nextInt(9));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedToken.toString();
    }

}
