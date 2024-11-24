package ru.nspk.task8.controller;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nspk.task8.dao.model.Account;
import ru.nspk.task8.dao.model.Transaction;
import ru.nspk.task8.service.AccountService;
import ru.nspk.task8.service.IPaymentSystemService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;
    private final IPaymentSystemService paymentSystemService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Transaction>> getAccountHistory(
            @PathVariable("id") long id,
            @RequestParam(value = "from", required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(value = "to", required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime to) {
        List<Transaction> result = paymentSystemService.getAccountHistory(id, from, to);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount() {
        Account result = accountService.createEmptyAccount();
        return ResponseEntity.ok(result);
    }
}
