package com.app.atmsimulation.validator;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Withdraw;
import com.app.atmsimulation.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class WithdrawValidator implements Validator {

    @Autowired
    WithdrawRepository withdrawRepository;

    @Override
    public boolean supports(Class<?> aClass) {

        return Withdraw.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Withdraw withdraw = (Withdraw) o;
        Account account = withdraw.getAccount();
        Integer balance = account.getBalance() - withdraw.getAmount();

        if (balance < 0) {
            errors.rejectValue("amount","Insufficient Balance");
        }
    }
}
