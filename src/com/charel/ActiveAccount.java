package com.charel;

import java.util.List;

public class ActiveAccount {

    private List<Account> accounts;

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public boolean verifyAccount(String accountNumber, String pin) {
        Account verifiedAccount = accounts.stream()
                .filter(account -> accountNumber.equals(account.getAccountNumber()) && pin.equals(account.getPin()))
                .findAny()
                .orElse(null);

        if (verifiedAccount != null) {
            return true;
        }

        return false;
    }
}
