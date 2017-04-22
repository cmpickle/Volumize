package com.cmpickle.volumize.data.dto;

import com.cmpickle.volumize.data.entity.ScheduleEvent;

import java.io.Serializable;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/22/2017.
 */

public class ScheduleEventInfo implements Serializable {

    private int id;
    private int option;
    private int amount;
    private boolean vibrate;
    private int days;
    private int hour;
    private int minute;
    private boolean repeatWeekly;
    private boolean active;

    public ScheduleEventInfo(ScheduleEvent scheduleEvent) {
        id = scheduleEvent.getRowId();
        option = scheduleEvent.getOption();
        amount = scheduleEvent.getAmount();
        vibrate = scheduleEvent.isVibrate();
        days = scheduleEvent.getHour();
        hour = scheduleEvent.getHour();
        minute = scheduleEvent.getMinute();
        repeatWeekly = scheduleEvent.isRepeatWeekly();
        active = scheduleEvent.isActive();
    }
}
