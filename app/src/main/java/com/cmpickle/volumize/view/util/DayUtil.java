package com.cmpickle.volumize.view.util;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/22/2017.
 */

public class DayUtil {

    public static boolean isSunday(int days) {
        return ((days>>>0)&1)==1;
    }

    public static boolean isMonday(int days) {
        return ((days>>>1)&1)==1;
    }

    public static boolean isTuesday(int days) {
        return ((days>>>2)&1)==1;
    }

    public static boolean isWednesday(int days) {
        return ((days>>>3)&1)==1;
    }

    public static boolean isThursday(int days) {
        return ((days>>>4)&1)==1;
    }

    public static boolean isFriday(int days) {
        return ((days>>>5)&1)==1;
    }

    public static boolean isSaturday(int days) {
        return ((days>>>6)&1)==1;
    }
}
