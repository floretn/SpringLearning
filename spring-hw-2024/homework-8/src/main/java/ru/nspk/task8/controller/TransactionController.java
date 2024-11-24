package ru.nspk.task8.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nspk.task8.controller.dto.TransactionDto;
import ru.nspk.task8.dao.model.Transaction;
import ru.nspk.task8.service.IPaymentSystemService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("transaction")
public class TransactionController {

    private final IPaymentSystemService paymentSystemService;

    @PostMapping("/create")
    public ResponseEntity<Transaction> createEntity(@RequestBody TransactionDto transactionDto) {
        Transaction transaction = paymentSystemService.makeTransaction(
                transactionDto.creditId(), transactionDto.debitId(), transactionDto.sum());
        return ResponseEntity.ok(transaction);
    }

    @PutMapping("/{id}/reverse")
    public ResponseEntity<Transaction> reverseEntity(@PathVariable long id) {
        Transaction transaction = paymentSystemService.reverseTransaction(id);
        return ResponseEntity.ok(transaction);
    }
}
