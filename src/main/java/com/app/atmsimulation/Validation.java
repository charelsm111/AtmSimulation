package com.app.atmsimulation;

public class Validation {

    public static boolean validateLengthDigit(String input, int length) {
        if (input.length() < length) {
            return false;
        }

        return true;
    }

    public static boolean validateInputIsNumeric(String input) {
        if (!input.matches("[0-9]+")) {
            return false;
        }

        return true;
    }

    public static boolean validateMaximumDraw(int amount) {
        if (amount >= 1000) {
            return false;
        }

        return true;
    }

    public static boolean  validateInputIsTenMultiplied(int input) {
        if (input % 10 != 0) {
            return false;
        }

        return true;
    }

    public static boolean validateMinimumDraw(int amount) {
        if (amount < 1) {
            return false;
        }

        return true;
    }
}
