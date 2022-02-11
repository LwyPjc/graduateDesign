package com.hospital.appointment.utils;

public class CommUtils {
    public static boolean checkIsNull(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        return false;
    }
}
