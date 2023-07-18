package com.gildedrose;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GetDateTime {
    LocalTime localTime = LocalTime.now();
    String textTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH));
    LocalTime parsedTime = LocalTime.parse(textTime, DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH));

    LocalDate localDate = LocalDate.now();
    String textDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH));
    LocalDate parsedDate = LocalDate.parse(textDate, DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH));

    public LocalDate getLocalDate() {
        return parsedDate;
    }

    public LocalTime getLocalTime() {
        return parsedTime;
    }
}
