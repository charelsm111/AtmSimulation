package com.app.atmsimulation.validator;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Transfer;
import com.app.atmsimulation.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TransferValidator implements Validator {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> aClass) {

        return Transfer.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "amount", "transfer.amount.empty", "Amount cannot be empty!");
        ValidationUtils.rejectIfEmpty(errors, "destinationAccountNumber", "transfer.destination.empty", "Account number cannot be empty!");

        Transfer transfer = (Transfer) o;
        Account destAccount = accountRepository.findByAccountNumber(transfer.getDestinationAccountNumber());

        if (destAccount == null) {
            errors.rejectValue("destinationAccountNumber","destinationAccountNumber.notFound");
        }

        if (transfer.getAmount() < 1 ) {
            errors.rejectValue("amount","withdrawAmount.min");
        }

        if (transfer.getAmount() % 10 != 0) {
            errors.rejectValue("amount","withdrawAmount.tenMultiplied");
        }

        if (transfer.getAmount() > 1000) {
            errors.rejectValue("amount","withdrawAmount.max");
        }

        Account account = transfer.getAccount();
        Integer balance = account.getBalance() - transfer.getAmount();

        if (balance < 0) {
            errors.rejectValue("amount","withdrawAmount.insufficientBalance");
        }
    }
}
