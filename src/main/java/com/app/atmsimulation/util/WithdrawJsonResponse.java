package com.app.atmsimulation.util;

import com.app.atmsimulation.model.Withdraw;

import java.util.List;

public class WithdrawJsonResponse {

    private Withdraw withdraw;
    private boolean isValid;
    private List<String> errorMessages;

    public void setWithdraw(Withdraw withdraw) {
        this.withdraw = withdraw;
    }

    public Withdraw getWithdraw() {
        return withdraw;
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