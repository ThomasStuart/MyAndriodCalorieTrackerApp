package com.example.androidcalorietracker.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeGenerator {

    public static String getDateDatabaseKey(){
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy#MM#dd");
        String key1 = dateFormat1.format(cal1.getTime());

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd");
        String key2 = dateFormat2.format(cal1.getTime());

        return key1;
    }

    public static String getTimeStamp(){
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        return dateFormat.format(cal1.getTime());
    }
}
