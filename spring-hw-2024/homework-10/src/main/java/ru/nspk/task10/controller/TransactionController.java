package ru.nspk.task10.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.nspk.task10.controller.ps.dto.TransactionDto;
import ru.nspk.task10.dao.model.Transaction;
import ru.nspk.task10.service.IPaymentSystemService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("transaction")
public class TransactionController {

    private final IPaymentSystemService paymentSystemService;

    @PostMapping("/create")
    public Mono<Transaction> createEntity(@RequestBody TransactionDto transactionDto) {
        return paymentSystemService.makeTransaction(
                transactionDto.creditId(), transactionDto.debitId(), transactionDto.sum());
    }

    @PutMapping("/{id}/reverse")
    public Mono<Transaction> reverseEntity(@PathVariable long id) {
        return paymentSystemService.reverseTransaction(id);
    }
}
