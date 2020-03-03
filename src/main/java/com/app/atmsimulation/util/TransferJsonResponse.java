package com.app.atmsimulation.util;

import com.app.atmsimulation.model.Transfer;

import java.util.List;

public class TransferJsonResponse {
    private Transfer transfer;
    private boolean isValid;
    private List<String> errorMessages;

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
