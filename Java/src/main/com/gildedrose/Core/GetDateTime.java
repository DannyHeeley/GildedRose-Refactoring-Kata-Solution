package com.gildedrose.Core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GetDateTime {
    private LocalTime parseLocalTime() {
        LocalTime localTime = LocalTime.now();
        String textTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH));
        return LocalTime.parse(textTime, DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH));
    }

    private LocalDate parseLocalDate() {
        LocalDate localDate = LocalDate.now();
        String textDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH));
        return LocalDate.parse(textDate, DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH));
    }

    public LocalDate getLocalDate() {
        return parseLocalDate();
    }

    public LocalTime getLocalTime() {
        return parseLocalTime();
    }
}
