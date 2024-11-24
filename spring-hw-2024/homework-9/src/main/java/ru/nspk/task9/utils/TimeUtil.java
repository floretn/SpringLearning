package ru.nspk.task9.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeUtil {

    public static final LocalDateTime EPOCH_DATETIME = LocalDateTime.of(LocalDate.EPOCH, LocalTime.MIN);

    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}
