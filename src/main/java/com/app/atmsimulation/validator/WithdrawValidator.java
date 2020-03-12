package com.app.atmsimulation.validator;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Withdraw;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class WithdrawValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {

        return Withdraw.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "amount", "withdraw.amount.empty", "Amount cannot be empty!");

        Withdraw withdraw = (Withdraw) o;
        if (withdraw.getAmount() < 1 ) {
            errors.rejectValue("amount","withdrawAmount.min");
        }

        if (withdraw.getAmount() % 10 != 0) {
            errors.rejectValue("amount","withdrawAmount.tenMultiplied");
        }

        if (withdraw.getAmount() > 1000) {
            errors.rejectValue("amount","withdrawAmount.max");
        }

        Account account = withdraw.getAccount();
        Integer balance = account.getBalance() - withdraw.getAmount();

        if (balance < 0) {
            errors.rejectValue("amount","withdrawAmount.insufficientBalance");
        }
    }
}
