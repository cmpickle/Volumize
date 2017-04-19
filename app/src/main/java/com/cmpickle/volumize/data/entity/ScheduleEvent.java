package com.cmpickle.volumize.data.entity;

import com.cmpickle.volumize.data.db.VolumizeDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/18/2017.
 */

@Table(name = "ScheduleEvent", database = VolumizeDatabase.class)
public class ScheduleEvent extends BaseModel {

    @PrimaryKey (autoincrement = true)
    private int rowId;

    @Column
    private int option;

    @Column
    private int amount;

    @Column
    private boolean vibrate;

    @Column
    private int days;

    @Column
    private int hour;

    @Column
    private int minute;

    @Column
    private boolean repeatWeekly;

    @Column
    private boolean active;

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
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

    public void setDays(int days) {
        this.days = days;
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
}
