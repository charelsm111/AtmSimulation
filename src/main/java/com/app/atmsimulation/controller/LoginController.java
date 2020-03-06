package com.app.atmsimulation.controller;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.service.AccountService;
import com.app.atmsimulation.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private LoginValidator loginValidator;

    @GetMapping({"/", "/login"})
    public String login(Model model) {

        model.addAttribute("account", new Account());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("account") @Valid Account account, BindingResult result, Model model, HttpSession session)
    {
        loginValidator.validate(account, result);
        if (result.hasErrors()) {

            return "login";
        }

        Account loggedInAccount = accountService.login(account.getAccountNumber(), account.getPin());

        if (loggedInAccount != null) {
            session.setAttribute("account", loggedInAccount);
            model.addAttribute("account", loggedInAccount);

            return "redirect:/account";
        } else {

            return "redirect:/login";
        }
    }

    @PostMapping("/logout")
    public String logout (HttpSession session) {
        session.removeAttribute("account");

        return "redirect:/login";
    }
}
