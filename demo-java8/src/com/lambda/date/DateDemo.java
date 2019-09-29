package com.lambda.date;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/26---16:34
 **/
public class DateDemo {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date.get(ChronoField.DAY_OF_YEAR));
        date.format(DateTimeFormatter.BASIC_ISO_DATE);
    }
}
