package com.hospital.appointment.utils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class CommUtils {
    public static boolean checkIsNull(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean checkIsNotNull(String str){
        return !checkIsNull(str);
    }
    public static Timestamp getTimestamp(){
        Timestamp timestamp = new Timestamp(new Date().getTime());
        return timestamp;
    }

    public static String generateUUID(){
        String uuid=UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }

}
