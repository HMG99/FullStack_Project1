package com.digi.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Base64;

public class TokenGenerator {

    public static String generateVerificationCode() {
        return RandomStringUtils.random(6, true, true);
    }

    public static String passwordEncode(String password) {
        return Base64.getUrlEncoder().encodeToString(password.getBytes());
    }

    public static String resetTokenGenerator() {
        return RandomStringUtils.random(8, true, true);
    }


}
