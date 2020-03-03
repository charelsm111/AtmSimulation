package com.app.atmsimulation.controller;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Transfer;
import com.app.atmsimulation.model.Withdraw;
import com.app.atmsimulation.service.TransferService;
import com.app.atmsimulation.service.WithdrawService;
import com.app.atmsimulation.util.TransferJsonResponse;
import com.app.atmsimulation.util.WithdrawJsonResponse;
import com.app.atmsimulation.validator.AccountExistenceValidator;
import com.app.atmsimulation.validator.BalanceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AccountController {

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private TransferService transferService;

    @Autowired
    private AccountExistenceValidator accountExistenceValidator;

    @Autowired
    private BalanceValidator balanceValidator;

    BaseControllerImpl baseController = new BaseControllerImpl();

    @GetMapping("/account")
    public String index(HttpSession session) {

        if (baseController.authenticateAccount(session)) {
            return "account/index";
        }

        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout (HttpSession session) {
        session.removeAttribute("account");

        return "redirect:/login";
    }

    @GetMapping("/withdraw")
    public String withdraw(HttpSession session) {
        if (baseController.authenticateAccount(session)) {
            return "account/withdraw";
        }

        return "redirect:/login";
    }

    @PostMapping("/withdraw")
    @ResponseBody
    public WithdrawJsonResponse withdraw(@ModelAttribute Withdraw withdraw, HttpSession httpSession, BindingResult result) {
        Account account = (Account) httpSession.getAttribute("account");
        withdraw.setAccount(account);
        balanceValidator.validate(withdraw, result);

        WithdrawJsonResponse response = new WithdrawJsonResponse();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getCode())
                    .collect(Collectors.toList());

            response.setValid(false);
            response.setErrorMessages(errors);
        } else {
            withdrawService.save(withdraw);
            response.setValid(true);
        }

        return response;
    }

    @GetMapping("/other-withdraw")
    public String otherWithdraw(HttpSession session) {
        if (baseController.authenticateAccount(session)) {
            return "account/other-withdraw";
        }

        return "redirect:/login";
    }

    @GetMapping("/transfer")
    public String transfer(HttpSession session) {
        if (baseController.authenticateAccount(session)) {
            return "account/transfer";
        }

        return "redirect:/login";
    }

    @PostMapping("/check")
    @ResponseBody
    public TransferJsonResponse check(@ModelAttribute Transfer transfer, HttpSession httpSession, BindingResult result) {
        accountExistenceValidator.validate(transfer, result);

        TransferJsonResponse response = new TransferJsonResponse();
        if (result.hasErrors()) {
            response.setValid(false);
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getCode())
                    .collect(Collectors.toList());

            response.setErrorMessages(errors);
        } else {
            response.setValid(true);
            response.setTransfer(transfer);
        }

        return response;
    }

    @PostMapping("/transfer")
    @ResponseBody
    public TransferJsonResponse transfer(@ModelAttribute Transfer transfer, HttpSession httpSession, BindingResult result) {
        Account account = (Account) httpSession.getAttribute("account");
        transfer.setAccount(account);
        balanceValidator.validate(transfer, result);

        TransferJsonResponse response = new TransferJsonResponse();
        if (result.hasErrors()) {
            response.setValid(false);
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getCode())
                    .collect(Collectors.toList());

            response.setErrorMessages(errors);
        } else {
            transferService.save(transfer);
            response.setValid(true);
        }

        return response;
    }
}
