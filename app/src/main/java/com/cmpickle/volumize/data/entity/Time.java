package com.cmpickle.volumize.data.entity;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/18/2017.
 */

public class Time {
    private int hour;
    private int minute;

    public Time() {
        this.hour=0;
        this.minute=0;
    }

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String toString() {
        return hour + ":" + String.format("%2d", minute);
    }
}
