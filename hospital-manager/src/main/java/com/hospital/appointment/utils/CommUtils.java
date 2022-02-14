package com.hospital.appointment.utils;

import java.sql.Timestamp;
import java.util.Date;

public class CommUtils {
    public static boolean checkIsNull(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        return false;
    }

    public static Timestamp getTimestamp(){
        Timestamp timestamp = new Timestamp(new Date().getTime());
        return timestamp;
    }

}
