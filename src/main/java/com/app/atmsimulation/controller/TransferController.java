package com.app.atmsimulation.controller;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Transfer;
import com.app.atmsimulation.service.TransferService;
import com.app.atmsimulation.validator.TransferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class TransferController {

    @Autowired
    private TransferService transferService;

    @Autowired
    private TransferValidator transferValidator;

    BaseControllerImpl baseController = new BaseControllerImpl();

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
}
