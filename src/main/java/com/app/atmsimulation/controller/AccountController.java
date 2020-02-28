package com.app.atmsimulation.controller;

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
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/logout")
    public String logout (HttpSession session) {
        session.removeAttribute("account");

        return "redirect:/login";
    }

    @GetMapping("/withdraw")
    public String withdraw() {

        return "account/withdraw";
    }

    @PostMapping("/withdraw")
    public String withdraw(@ModelAttribute Withdraw withdraw) {
        Withdraw newWithdraw = withdrawService.save(withdraw);

        if (newWithdraw != null) {
            return "redirect:/account";
        } else {
            return "redirect:/withdraw";
        }
    }
}
