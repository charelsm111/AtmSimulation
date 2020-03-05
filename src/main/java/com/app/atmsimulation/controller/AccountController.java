package com.app.atmsimulation.controller;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Transaction;
import com.app.atmsimulation.model.Transfer;
import com.app.atmsimulation.model.Withdraw;
import com.app.atmsimulation.service.TransactionService;
import com.app.atmsimulation.service.TransferService;
import com.app.atmsimulation.service.WithdrawService;
import com.app.atmsimulation.util.TransferJsonResponse;
import com.app.atmsimulation.validator.AccountExistenceValidator;
import com.app.atmsimulation.validator.BalanceValidator;
import com.app.atmsimulation.validator.WithdrawValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AccountController {

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private TransferService transferService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountExistenceValidator accountExistenceValidator;

    @Autowired
    private BalanceValidator balanceValidator;

    @Autowired
    private WithdrawValidator withdrawValidator;

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
    public String withdraw(@ModelAttribute("withdraw") @Valid Withdraw withdraw, BindingResult result, HttpSession httpSession) {
        Account account = (Account) httpSession.getAttribute("account");
        withdraw.setAccount(account);
        withdrawValidator.validate(withdraw, result);

        if (result.hasErrors()) {

            return "account/other-withdraw";
        }

        withdrawService.save(withdraw);

        return "redirect:/account";
    }

    @GetMapping("/other-withdraw")
    public String otherWithdraw(Model model, HttpSession session) {
        if (baseController.authenticateAccount(session)) {

            model.addAttribute("withdraw", new Withdraw());
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

    @GetMapping("/history")
    public String history(@RequestParam(defaultValue = "10") Integer transaction, HttpSession httpSession, ModelMap model) {

        if (baseController.authenticateAccount(httpSession)) {
            List<Transaction> transactions = transactionService.findLastTransaction(transaction);
            model.put("transactions", transactions);

            return "account/history";
        }

        return "redirect:/login";
    }
}
