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

    public static int setFlagSunday(boolean flag, int days) {
        if(flag) {
            return days | (1 << 0);
        } else {
            return days & ~(1 << 0);
        }
    }

    public static int setFlagMonday(boolean flag, int days) {
        if(flag) {
            return days | (1 << 1);
        } else {
            return days & ~(1 << 1);
        }
    }

    public static int setFlagTuesday(boolean flag, int days) {
        if(flag) {
            return days | (1 << 2);
        } else {
            return days & ~(1 << 2);
        }
    }

    public static int setFlagWednesday(boolean flag, int days) {
        if(flag) {
            return days | (1 << 3);
        } else {
            return days & ~(1 << 3);
        }
    }

    public static int setFlagThursday(boolean flag, int days) {
        if(flag) {
            return days | (1 << 4);
        } else {
            return days & ~(1 << 4);
        }
    }

    public static int setFlagFriday(boolean flag, int days) {
        if(flag) {
            return days | (1 << 5);
        } else {
            return days & ~(1 << 5);
        }
    }

    public static int setFlagSaturday(boolean flag, int days) {
        if(flag) {
            return days | (1 << 6);
        } else {
            return days & ~(1 << 6);
        }
    }
}
