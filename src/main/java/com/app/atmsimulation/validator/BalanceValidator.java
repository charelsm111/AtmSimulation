package com.app.atmsimulation.validator;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BalanceValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {

        return Transaction.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Transaction transaction = (Transaction) o;
        Account account = transaction.getAccount();
        Integer balance = account.getBalance() - transaction.getAmount();

        if (balance < 0) {
            errors.rejectValue("amount","Insufficient Balance");
        }
    }
}
