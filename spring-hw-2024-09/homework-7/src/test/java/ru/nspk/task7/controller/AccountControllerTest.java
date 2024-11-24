package ru.nspk.task7.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.nspk.task7.model.AccountHistoryRecord;
import ru.nspk.task7.model.AccountId;
import ru.nspk.task7.service.AccountHistoryService;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private AccountHistoryService mockAccountHistory;

    @InjectMocks
    private AccountController accountController;

    @Test
    void test_rethrow_ex() {
        when(mockAccountHistory.getHistory(any(), any(), any())).thenThrow(new IllegalArgumentException("test"));
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> accountController.getAccountHistory(1, "2024-10-10", "2024-10-27"));

        assertTrue(illegalArgumentException.getMessage().contains("test"));
        verify(mockAccountHistory)
                .getHistory(
                        new AccountId(1),
                        LocalDate.parse("2024-10-10", DateTimeFormatter.ISO_DATE),
                        LocalDate.parse("2024-10-27", DateTimeFormatter.ISO_DATE));
    }

    @Test
    void test_return_value() {
        List<AccountHistoryRecord> history = new ArrayList<>();
        history.add(new AccountHistoryRecord(null, null, null, 0));
        when(mockAccountHistory.getHistory(any(), any(), any())).thenReturn(history);
        ResponseEntity<List<AccountHistoryRecord>> result =
                assertDoesNotThrow(() -> accountController.getAccountHistory(1, "2024-10-10", "2024-10-27"));

        assertSame(result.getBody(), history);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        verify(mockAccountHistory)
                .getHistory(
                        new AccountId(1),
                        LocalDate.parse("2024-10-10", DateTimeFormatter.ISO_DATE),
                        LocalDate.parse("2024-10-27", DateTimeFormatter.ISO_DATE));
    }

    @Test
    void test_empty_date_params() {
        List<AccountHistoryRecord> history = new ArrayList<>();
        history.add(new AccountHistoryRecord(null, null, null, 0));
        when(mockAccountHistory.getHistory(any(), any(), any())).thenReturn(history);
        ResponseEntity<List<AccountHistoryRecord>> result =
                assertDoesNotThrow(() -> accountController.getAccountHistory(1, null, null));

        assertSame(result.getBody(), history);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        verify(mockAccountHistory).getHistory(new AccountId(1), LocalDate.EPOCH, LocalDate.now());
    }
}
