package com.app.atmsimulation.controller;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Transaction;
import com.app.atmsimulation.model.Transfer;
import com.app.atmsimulation.model.Withdraw;
import com.app.atmsimulation.service.TransactionService;
import com.app.atmsimulation.service.TransferService;
import com.app.atmsimulation.service.WithdrawService;
import com.app.atmsimulation.validator.TransferValidator;
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

@Controller
public class AccountController {

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private TransferService transferService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private WithdrawValidator withdrawValidator;

    @Autowired
    private TransferValidator transferValidator;

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
    public String transfer(Model model, HttpSession session) {
        if (baseController.authenticateAccount(session)) {

            model.addAttribute("transfer", new Transfer());
            return "account/transfer";
        }

        return "redirect:/login";
    }

    @PostMapping("/transfer")
    public String transfer(@ModelAttribute("transfer") @Valid Transfer transfer, BindingResult result, HttpSession httpSession) {
        Account account = (Account) httpSession.getAttribute("account");
        transfer.setAccount(account);
        transferValidator.validate(transfer, result);

        if (result.hasErrors()) {

            return "account/transfer";
        }

        transferService.save(transfer);

        return "redirect:/account";
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
