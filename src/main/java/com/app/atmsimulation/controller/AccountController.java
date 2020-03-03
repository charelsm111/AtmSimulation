package com.app.atmsimulation.controller;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Withdraw;
import com.app.atmsimulation.service.AccountService;
import com.app.atmsimulation.service.WithdrawService;
import com.app.atmsimulation.util.WithdrawJsonResponse;
import com.app.atmsimulation.validator.WithdrawValidator;
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
    private AccountService accountService;

    @Autowired
    private WithdrawService withdrawService;

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
    @ResponseBody
    public WithdrawJsonResponse withdraw(@ModelAttribute Withdraw withdraw, HttpSession httpSession, BindingResult result) {
        Account account = (Account) httpSession.getAttribute("account");
        withdraw.setAccount(account);
        withdrawValidator.validate(withdraw, result);

        WithdrawJsonResponse response = new WithdrawJsonResponse();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getCode())
                    .collect(Collectors.toList());

            response.setValid(false);
            response.setErrorMessages(errors);
        } else {
            Withdraw newWithdraw = withdrawService.save(withdraw);
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
}
