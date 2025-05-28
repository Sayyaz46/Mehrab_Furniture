package com.example.mehrabfurniture;

import java.util.regex.Pattern;

public class PasswordValidator {

    // Regex pattern for a strong password
    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

    public static boolean isStrongPassword(String password) {
        return Pattern.matches(PASSWORD_PATTERN, password);
    }

    public static void main(String[] args) {
        String password = "Password123!"; // you can change this to test other cases

        if (isStrongPassword(password)) {
            System.out.println("✅ Strong password!");
        } else {
            System.out.println("❌ Weak password. Try again.");
        }
    }
}
