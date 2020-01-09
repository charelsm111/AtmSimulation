package com.app;

public class Validation {

    private boolean isError = false;
    private String message;

    public void setIsError(boolean isError) {
        this.isError = isError;
    }

    public boolean getIsError() {
        return isError;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
