package com.example.androidcalorietracker.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeGenerator {

    public static String getDateDatabaseKey(){
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy#MM#dd");
        return dateFormat1.format(cal1.getTime());
    }

    public static String getTimeStamp(){
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        return dateFormat.format(cal1.getTime());
    }

    public static int getMilitaryHoursInteger(){
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH");

        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);

        String time = displayFormat.format(cal1.getTime());

        return Integer.parseInt(time);
    }

    public static String getDayOfWeekAsWord() {
        long timeInMillis = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeInMillis);
        return getDayOfWeek( cal.get(Calendar.DAY_OF_WEEK));
    }

    public static String getDayOfWeekAsInt() {
        long timeInMillis = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        return dateFormat.format(cal.getTime());
    }

    public static String getMonthOfYear() {
        long timeInMillis = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM");
        return dateFormat.format(cal.getTime());
    }

    private static String getDayOfWeek(int value) {
        String day = "";
        switch (value) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }
        return day;
    }


}
