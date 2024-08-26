package com.example.orderwise.common.config;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";

    private static final String ALL_CHARACTERS = UPPER_CASE + LOWER_CASE + DIGITS + SPECIAL_CHARACTERS;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generatePassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("Password length should be at least 8 characters");
        }

        StringBuilder password = new StringBuilder(length);

        // Ensure password contains at least one character from each category
        password.append(getRandomCharacter(UPPER_CASE));
        password.append(getRandomCharacter(LOWER_CASE));
        password.append(getRandomCharacter(DIGITS));
        password.append(getRandomCharacter(SPECIAL_CHARACTERS));

        // Fill the remaining characters randomly
        for (int i = 4; i < length; i++) {
            password.append(getRandomCharacter(ALL_CHARACTERS));
        }

        // Shuffle the characters to ensure randomness
        return shuffleString(password.toString());
    }

    private static char getRandomCharacter(String characters) {
        int index = RANDOM.nextInt(characters.length());
        return characters.charAt(index);
    }

    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }
}
