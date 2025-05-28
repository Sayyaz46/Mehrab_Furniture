package com.example.mehrabfurniture;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    public static void main(String[] args) {
        // Sample email to validate
        String email = "test@example.com";

        // Regex pattern for a valid email
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        // Compile the regex
        Pattern pattern = Pattern.compile(regex);

        // Match the pattern against the email
        Matcher matcher = pattern.matcher(email);

        // Check if it matches
        if (matcher.matches()) {
            System.out.println(email + " is a valid email address.");
        } else {
            System.out.println(email + " is NOT a valid email address.");
        }
    }
}
