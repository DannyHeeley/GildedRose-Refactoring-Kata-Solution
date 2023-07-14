package com.gildedrose;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GetDateTime {
    LocalTime localTime = LocalTime.now();
    DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH);

    LocalDate localDate = LocalDate.now();
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);

    public DateTimeFormatter getLocalDate() {
        return formatDate;
    }

    public DateTimeFormatter getLocalTime() {
        return formatTime;
    }

}
