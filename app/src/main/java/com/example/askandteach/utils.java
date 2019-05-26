package com.example.askandteach;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class utils {
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");


    public static String timestampToDatestring(float timestamp){
        Date date=new Date((long)timestamp);
        return dateFormat.format(date);
    }

}
