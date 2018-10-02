package com.company;
import java.time.ZoneId;
import java.time.ZonedDateTime;
public class Main {


    public static void main(String[] args) {
	// write your code here
       ZoneId zoneId = ZoneId.of("UTC");
        ZonedDateTime today = ZonedDateTime.now();
        ZonedDateTime birthday = ZonedDateTime.of(1991, 2 ,21 , 0 , 0 , 0 , 0,zoneId);
        System.out.println(birthday);
        int year = today.getYear() - birthday.getYear();
        int days = year * 365 ;
        days -= birthday.getDayOfYear();
        days += today.getDayOfYear();
        System.out.println(days);

    }
}
