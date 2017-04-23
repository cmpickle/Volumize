package com.cmpickle.volumize.data.dto;

import com.cmpickle.volumize.data.entity.ScheduleEvent;

import java.io.Serializable;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/22/2017.
 */

public class ScheduleEventInfo implements Serializable {

    private String id;
    private int option;
    private int amount;
    private boolean vibrate;
    private int days;
    private int hour;
    private int minute;
    private boolean repeatWeekly;
    private boolean active;

    public ScheduleEventInfo() {
        id = getId();
        option = 0;
        amount = 12;
        vibrate = true;
        days = 0;
        hour = 7;
        minute = 0;
        repeatWeekly = false;
        active = true;
    }

    public ScheduleEventInfo(ScheduleEvent scheduleEvent) {
        id = scheduleEvent.getId();
        option = scheduleEvent.getOption();
        amount = scheduleEvent.getAmount();
        vibrate = scheduleEvent.isVibrate();
        days = scheduleEvent.getDays();
        hour = scheduleEvent.getHour();
        minute = scheduleEvent.getMinute();
        repeatWeekly = scheduleEvent.isRepeatWeekly();
        active = scheduleEvent.isActive();
    }

    public ScheduleEventInfo(ScheduleEventInfo eventInfo) {
        id = eventInfo.getId();
        option = eventInfo.getOption();
        amount = eventInfo.getAmount();
        vibrate = eventInfo.isVibrate();
        days = eventInfo.getDays();
        hour = eventInfo.getHour();
        minute = eventInfo.getMinute();
        repeatWeekly = eventInfo.isRepeatWeekly();
        active = eventInfo.isActive();
    }

    public String getId() {
        return id;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
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

    public boolean isRepeatWeekly() {
        return repeatWeekly;
    }

    public void setRepeatWeekly(boolean repeatWeekly) {
        this.repeatWeekly = repeatWeekly;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ScheduleEvent getScheduleEvent() {
        ScheduleEvent scheduleEvent = new ScheduleEvent();
        if(id != null) {
            scheduleEvent.setId(id);
        } else {
            id = scheduleEvent.getId();
        }
        update(scheduleEvent);
        return scheduleEvent;
    }

    @Override
    public boolean equals(Object o) {
        if(this ==o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        ScheduleEventInfo scheduleEventInfo = (ScheduleEventInfo) o;
        return id.equals(scheduleEventInfo.getId()) &&
                option == scheduleEventInfo.option &&
                amount == scheduleEventInfo.amount &&
                vibrate == scheduleEventInfo.vibrate &&
                days == scheduleEventInfo.days &&
                hour == scheduleEventInfo.hour &&
                minute == scheduleEventInfo.minute &&
                repeatWeekly == scheduleEventInfo.repeatWeekly &&
                active == scheduleEventInfo.active;
    }

    public void update(ScheduleEvent scheduleEvent) {
        scheduleEvent.setOption(option);
        scheduleEvent.setAmount(amount);
        scheduleEvent.setVibrate(vibrate);
        scheduleEvent.setDays(days);
        scheduleEvent.setHour(hour);
        scheduleEvent.setMinute(minute);
        scheduleEvent.setRepeatWeekly(repeatWeekly);
        scheduleEvent.setActive(active);
    }
}
