package ru.nspk.task6.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeUtil {
    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
