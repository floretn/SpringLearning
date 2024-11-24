package ru.nspk.task7.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nspk.task7.model.AccountHistoryRecord;
import ru.nspk.task7.model.AccountId;
import ru.nspk.task7.service.AccountHistoryService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("history/account")
public class AccountController {

    private final AccountHistoryService accountHistoryService;

    @GetMapping("/{id}")
    public ResponseEntity<List<AccountHistoryRecord>> getAccountHistory(
            @PathVariable("id") long id,
            @RequestParam(value = "from", required = false) String from,
            @RequestParam(value = "to", required = false) String to) {
        List<AccountHistoryRecord> result = accountHistoryService.getHistory(
                new AccountId(id), parseDate(from, LocalDate.EPOCH), parseDate(to, LocalDate.now()));
        return ResponseEntity.ok(result);
    }

    private LocalDate parseDate(String date, LocalDate defaultValue) {
        if (date == null) {
            return defaultValue;
        }
        try {
            return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        } catch (Exception exception) {
            log.error("Error parse date: ", exception);
            return defaultValue;
        }
    }
}
