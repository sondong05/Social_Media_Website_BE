package com.backend.vinbook.utils;

import java.security.SecureRandom;

public class OtpUtil {
    private static final SecureRandom RANDOM = new SecureRandom();
    public static String generateNumericOtp(int length) {
        if (length <= 0) throw new IllegalArgumentException("length must be > 0");
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }
}
