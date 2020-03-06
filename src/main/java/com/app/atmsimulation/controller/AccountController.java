package com.app.atmsimulation.controller;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Transaction;
import com.app.atmsimulation.model.Transfer;
import com.app.atmsimulation.model.Withdraw;
import com.app.atmsimulation.service.TransactionService;
import com.app.atmsimulation.service.TransferService;
import com.app.atmsimulation.service.WithdrawService;
import com.app.atmsimulation.util.WithdrawJsonResponse;
import com.app.atmsimulation.validator.TransferValidator;
import com.app.atmsimulation.validator.WithdrawValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

    @GetMapping("/transfer")
    public String transfer(Model model, HttpSession session) {
        if (baseController.authenticateAccount(session)) {

            model.addAttribute("transfer", new Transfer());
            return "transfer/transfer";
        }

        return "redirect:/login";
    }

    @PostMapping("/transfer")
    public String transfer(@ModelAttribute("transfer") @Valid Transfer transfer, BindingResult result, HttpSession httpSession) {
        Account account = (Account) httpSession.getAttribute("account");
        transfer.setAccount(account);
        transferValidator.validate(transfer, result);

        if (result.hasErrors()) {

            return "transfer/transfer";
        }

        transferService.save(transfer);

        return "redirect:/account";
    }

    @GetMapping("/history")
    public String history(@RequestParam(name = "transaction", required = false)
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                  LocalDate transaction, HttpSession httpSession, ModelMap model) {

        if (baseController.authenticateAccount(httpSession)) {
            List<Transaction> transactions = transactionService.findByDateOrderByIdDesc(transaction);
            model.put("transactions", transactions);

            return "transaction/history";
        }

        return "redirect:/login";
    }
}
