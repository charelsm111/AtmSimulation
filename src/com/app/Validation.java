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

}