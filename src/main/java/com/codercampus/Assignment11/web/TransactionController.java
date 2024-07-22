package com.codercampus.Assignment11.web;

import com.codercampus.Assignment11.domain.Transaction;
import com.codercampus.Assignment11.service.TransactionService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public String getAllTransactions(Model model) {
        List<Transaction> transactions = transactionService.findAllTransactions();
        model.addAttribute("transactions", transactions);
        return "transactions";
    }

    @GetMapping("/transactions/{transactionId}")
    public String getTransactionById(@PathVariable Long transactionId, Model model) {
        Optional<Transaction> transactionOpt = transactionService.findTransactionById(transactionId);
        if (transactionOpt.isPresent()) {
            Transaction transaction = transactionOpt.get();
            if ("Credit".equals(transaction.getType())) {
                transaction.setAmount(transaction.getAmount().negate());
            }
            model.addAttribute("transaction", transaction);
            return "transaction-detail";
        } else {
            return "redirect:/transactions";
        }
    }
}