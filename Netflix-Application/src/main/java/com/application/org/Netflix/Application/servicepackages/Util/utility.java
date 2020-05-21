package com.application.org.Netflix.Application.servicepackages.Util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class utility {
    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String generateUserId(int length) {
        return generateRandomString(length);

    }

    public String generateAddressId(int length) {
        return generateRandomString(length);

    }

    private String generateRandomString(int length) {

        StringBuilder returnvalue = new StringBuilder(length);

        for(int i=0;i<length;i++) {
            returnvalue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnvalue);
    }
}
