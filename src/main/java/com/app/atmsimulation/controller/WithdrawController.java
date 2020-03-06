package com.app.atmsimulation.controller;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Withdraw;
import com.app.atmsimulation.service.WithdrawService;
import com.app.atmsimulation.util.WithdrawJsonResponse;
import com.app.atmsimulation.validator.WithdrawValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WithdrawController {

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private WithdrawValidator withdrawValidator;

    BaseControllerImpl baseController = new BaseControllerImpl();

    @GetMapping("/withdraw")
    public String withdraw(HttpSession session) {
        if (baseController.authenticateAccount(session)) {

            return "withdraw/withdraw";
        }

        return "redirect:/login";
    }

    @PostMapping("/withdraw")
    @ResponseBody
    public WithdrawJsonResponse withdraw(@ModelAttribute Withdraw withdraw, BindingResult result, HttpSession httpSession) {
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
            withdrawService.save(withdraw);
            response.setValid(true);
        }

        return response;
    }

    @PostMapping("/other-withdraw")
    public String otherWithdraw(@ModelAttribute("withdraw") @Valid Withdraw withdraw, BindingResult result, HttpSession httpSession) {
        Account account = (Account) httpSession.getAttribute("account");
        withdraw.setAccount(account);
        withdrawValidator.validate(withdraw, result);

        if (result.hasErrors()) {

            return "withdraw/other-withdraw";
        }

        withdrawService.save(withdraw);

        return "redirect:/account";
    }

    @GetMapping("/other-withdraw")
    public String otherWithdraw(Model model, HttpSession session) {
        if (baseController.authenticateAccount(session)) {

            model.addAttribute("withdraw", new Withdraw());
            return "withdraw/other-withdraw";
        }

        return "redirect:/login";
    }
}
