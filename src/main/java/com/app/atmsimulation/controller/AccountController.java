package com.app.atmsimulation.controller;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.service.AccountCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.BindException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AccountController {

    BaseControllerImpl baseController = new BaseControllerImpl();

    @Autowired
    AccountCsvService accountCsvService;

    @GetMapping("/account")
    public String index(HttpSession session) {

        if (baseController.authenticateAccount(session)) {
            return "account/index";
        }

        return "redirect:/login";
    }

    @GetMapping({"/", "/account/init"})
    public String init() {

        return "account/init";
    }

    @PostMapping("/account/init")
    public String init(@RequestParam("file") MultipartFile file, ModelMap model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                List<String> lines = reader.lines().collect(Collectors.toList());

                // accountCsvService is Singleton
                // Clear the list first to avoid duplicate from previous upload
                accountCsvService.getAccountList().clear();

                // Remove duplicate accounts from file
                accountCsvService.removeDuplicateAccountFromCsv(lines);

                int saved = 0;
                int duplicate = 0;
                for (Account account: accountCsvService.getAccountList()) {
                    // Check if account already in database
                    if (accountCsvService.findByAccountNumber(account.getAccountNumber()) == null) {
                        accountCsvService.save(account);
                        saved++;
                    } else {
                        duplicate++;
                    }
                }

                model.addAttribute("saved", "New account saved: " + saved);
                model.addAttribute("duplicate", "Duplicate account found: " + duplicate);
                model.addAttribute("account", new Account());

                return "login";

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
            }
        }

        return "account/init";
    }


}
