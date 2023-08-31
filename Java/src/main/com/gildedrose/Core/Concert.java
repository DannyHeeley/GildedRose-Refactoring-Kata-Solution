package com.gildedrose.Core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Concert {
    String concertName;
    LocalDate concertDate;
    LocalTime concertEndTime;

    public Concert(String concertName, String concertDate, String concertEndTime) {
        this.concertName = concertName;
        this.concertEndTime = LocalTime.parse(concertEndTime, DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH));
        this.concertDate = LocalDate.parse(concertDate, DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH));
    }

    public boolean isConcertOver(GetDateTime getDateTime) {
        LocalDate currentDate = getDateTime.getLocalDate();
        LocalTime currentTime = getDateTime.getLocalTime();
        return currentDate.isAfter(concertDate) || (currentDate.isEqual(concertDate) && currentTime.isAfter(concertEndTime));
    }
}

