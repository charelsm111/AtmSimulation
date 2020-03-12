package com.app.atmsimulation.validator;


import com.app.atmsimulation.repository.AccountRepository;
import com.app.atmsimulation.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> aClass) {

        return Account.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "accountNumber", "accountNumber.empty", "Account number is required!");
        ValidationUtils.rejectIfEmpty(errors, "pin", "pin.empty", "PIN is required!");

        Account account = (Account) o;
        if (account.getAccountNumber().length() < 6) {
            errors.rejectValue("accountNumber","accountNumber.minLength");
        }

        if (!account.getAccountNumber().matches("[0-9]+")) {
            errors.rejectValue("accountNumber","accountNumber.isNumeric");
        }

        if (account.getPin().length() < 6) {
            errors.rejectValue("pin","pin.minLength");
        }

        if (!account.getPin().matches("[0-9]+")) {
            errors.rejectValue("pin","pin.isNumeric");
        }

        Account account1 = accountRepository.findByAccountNumberAndPin(account.getAccountNumber(), account.getPin());
        if (account1 == null) {
            errors.rejectValue("accountNumber", "account.notFound");
        }

    }
}
