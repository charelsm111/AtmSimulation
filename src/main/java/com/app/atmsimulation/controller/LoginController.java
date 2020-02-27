package com.app.atmsimulation.controller;

import com.app.atmsimulation.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping({"/", "/login"})
    public String login() {

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Account account, ModelMap model)
    {
        model.addAttribute("accountNumber", account.getAccountNumber());

        return "home";
    }
}
