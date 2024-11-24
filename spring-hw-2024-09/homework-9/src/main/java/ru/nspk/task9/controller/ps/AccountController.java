package ru.nspk.task9.controller.ps;

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
import ru.nspk.task9.dao.model.ps.Account;
import ru.nspk.task9.dao.model.ps.Transaction;
import ru.nspk.task9.service.ps.AccountService;
import ru.nspk.task9.service.ps.IPaymentSystemService;

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
