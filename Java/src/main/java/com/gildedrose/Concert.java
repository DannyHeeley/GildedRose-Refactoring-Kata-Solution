package com.gildedrose;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// Time and date format:
//  dd/MM/yyyy
//  HH:mm:ss

public class Concert {
    String concertName;
    LocalDate concertDate;
    LocalTime concertEndTime;

    public Concert(String concertName, String concertDate, String concertEndTime, GetDateTime dateTime) {
        LocalTime concertEndTimeFormatted = LocalTime.parse(concertEndTime, dateTime.formatDate);
        LocalDate concertDateFormatted = LocalDate.parse(concertDate, dateTime.formatTime);
        this.concertName = concertName;
        this.concertEndTime = concertEndTimeFormatted;
        this.concertDate = concertDateFormatted;
    }
}

