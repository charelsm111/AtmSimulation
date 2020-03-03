package com.app.atmsimulation.util;

import com.app.atmsimulation.model.Account;
import java.util.List;

public class AccountJsonResponse {

    private Account account;
    private boolean isValid;
    private List<String> errorMessages;

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
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
