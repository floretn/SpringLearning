package ru.nspk.task10.controller;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nspk.task10.dao.model.Account;
import ru.nspk.task10.dao.model.Transaction;
import ru.nspk.task10.service.AccountService;
import ru.nspk.task10.service.IPaymentSystemService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;
    private final IPaymentSystemService paymentSystemService;

    @GetMapping("/{id}")
    public Flux<Transaction> getAccountHistory(
            @PathVariable("id") long id,
            @RequestParam(value = "from", required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(value = "to", required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime to) {
        return paymentSystemService.getAccountHistory(id, from, to);
    }

    @PostMapping("/create")
    public Mono<Account> createAccount() {
        return accountService.createEmptyAccount();
    }
}
