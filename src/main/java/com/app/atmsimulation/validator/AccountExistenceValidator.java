package com.app.atmsimulation.validator;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Transfer;
import com.app.atmsimulation.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountExistenceValidator implements Validator {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> aClass) {

        return Transfer.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Transfer transfer = (Transfer) o;
        Account account = accountRepository.findByAccountNumber(transfer.getDestinationAccountNumber());

        if (account == null) {
            errors.rejectValue("destinationAccountNumber","Account not found");
        }
    }
}
