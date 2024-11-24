package ru.nspk.task7.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nspk.task7.controller.dto.TransactionDto;
import ru.nspk.task7.model.AccountId;
import ru.nspk.task7.model.EntryId;
import ru.nspk.task7.service.AccountService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("entry")
public class EntryController {

    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<EntryId> createEntity(@RequestBody TransactionDto transactionDto) {
        AccountId debitId = new AccountId(transactionDto.debitId());
        AccountId creditId = new AccountId(transactionDto.creditId());
        long sum = transactionDto.sum();
        EntryId entryId = accountService.makeEntry(debitId, creditId, sum);
        return ResponseEntity.ok(entryId);
    }

    @PutMapping("/{id}/reverse")
    public ResponseEntity<EntryId> reverseEntity(@PathVariable long id) {
        EntryId entryId = accountService.makeReverseEntry(new EntryId(id));
        return ResponseEntity.ok(entryId);
    }
}
