package com.hancidev.hotelmanagementsystem.utility;

import java.security.SecureRandom;

public class RandomCodeGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int MAX_NUMBER = 9999;

    public static String generateRoomCode() {
        int number = RANDOM.nextInt(MAX_NUMBER + 1);
        return String.format("%04d", number);
    }
}
