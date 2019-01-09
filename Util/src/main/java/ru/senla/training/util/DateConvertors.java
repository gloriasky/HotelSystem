package ru.senla.training.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateConvertors {
    public static Date stringToDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDateOfRelease = LocalDate.parse(stringDate, formatter);
        Date date = Date.from(localDateOfRelease.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }
    public static String dateToString(Date date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate  localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDateTime.format(formatter);
    }
    public static java.sql.Date sqlDate(Date date){
        LocalDate  localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDateTime);
        return sqlDate;
    }
}
