package com.app.atmsimulation.controller;

import com.app.atmsimulation.model.Transaction;
import com.app.atmsimulation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    BaseControllerImpl baseController = new BaseControllerImpl();

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
