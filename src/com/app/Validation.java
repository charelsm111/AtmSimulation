package com.app;

/*
*   A class to handle error message
 */
class Validation {

    private boolean isError = false;
    private String message;

    void setIsError() {
        this.isError = true;
    }

    boolean getIsError() {
        return isError;
    }

    void setMessage(String message) {
        this.message = message;
    }

    String getMessage() {
        return message;
    }

    static Validation validateInputIsNumeric(String input) {
        Validation validation = new Validation();

        if (!input.matches("[0-9]+")) {
            validation.setIsError();
        }

        return validation;
    }

    static Validation validateInputLength(String input, Integer length) {
        Validation validation = new Validation();

        if (input.length() < length) {
            validation.setIsError();
            validation.setMessage("Account Number should have 6 digits length");
        }

        return validation;
    }

}
