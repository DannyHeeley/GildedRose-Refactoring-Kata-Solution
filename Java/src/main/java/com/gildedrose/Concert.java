package com.gildedrose;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Concert {
    String concertName;
    LocalDate concertDate;
    LocalTime concertEndTime;

    public Concert(String concertName, String concertDate, String concertEndTime, GetDateTime dateTime) {
        LocalTime concertEndTimeFormatted = LocalTime.parse(concertEndTime, dateTime.formatTime);
        LocalDate concertDateFormatted = LocalDate.parse(concertDate, dateTime.formatDate);
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        this.concertName = concertName;
        this.concertEndTime = concertEndTimeFormatted;
        this.concertDate = concertDateFormatted;
    }
}

