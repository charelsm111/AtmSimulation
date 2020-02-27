package com.app.atmsimulation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index (HttpSession session) {

        if (session.getAttribute("account") == null) {
            return "redirect:/login";
        } else {
            return "index";
        }
    }

    @PostMapping("/logout")
    public String logout (HttpSession session) {
        session.removeAttribute("account");

        return "redirect:/login";
    }
}
