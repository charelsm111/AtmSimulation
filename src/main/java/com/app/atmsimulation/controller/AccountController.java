package com.app.atmsimulation.controller;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Withdraw;
import com.app.atmsimulation.service.AccountService;
import com.app.atmsimulation.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private WithdrawService withdrawService;

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
    public String withdraw(@ModelAttribute Withdraw withdraw, HttpSession httpSession) {
        Account account = (Account) httpSession.getAttribute("account");
        withdraw.setAccount(account);
        Withdraw newWithdraw = withdrawService.save(withdraw);

        if (newWithdraw != null) {
            account.setBalance(account.getBalance() - withdraw.getAmount());
            Account newAccount = accountService.save(account);

            if (newAccount != null) {
                return "redirect:/account";
            }
        }

        return "redirect:/withdraw";
    }

    @GetMapping("/other-withdraw")
    public String otherWithdraw(HttpSession session) {
        if (baseController.authenticateAccount(session)) {
            return "account/other-withdraw";
        }

        return "redirect:/login";
    }
}
