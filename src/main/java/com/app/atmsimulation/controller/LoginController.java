package com.app.atmsimulation.controller;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private AccountService accountService;

    @GetMapping({"/", "/login"})
    public String login() {

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Account account, HttpSession session, Model model)
    {
        Account loggedInAccount = accountService.login(account.getAccountNumber(), account.getPin());

        if (loggedInAccount != null) {
            session.setAttribute("account", loggedInAccount);
            model.addAttribute("account", loggedInAccount);

            return "redirect:/index";
        } else {

            return "redirect:/login";
        }

    }
}
