package com.gildedrose;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GetDateTime {
    LocalTime localTime = LocalTime.now();
    DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH);
    String textTime = localTime.format(formatTime);
    LocalTime parsedTime = LocalTime.parse(textTime, formatTime);

    LocalDate localDate = LocalDate.now();
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
    String textDate = localDate.format(formatDate);
    LocalDate parsedDate = LocalDate.parse(textDate, formatDate);

    public LocalDate getLocalDate() {
        return parsedDate;
    }

    public LocalTime getLocalTime() {
        return parsedTime;
    }
}
