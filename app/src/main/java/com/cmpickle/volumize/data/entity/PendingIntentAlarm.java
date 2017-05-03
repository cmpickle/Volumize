package com.cmpickle.volumize.data.entity;

import com.cmpickle.volumize.data.db.VolumizeDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 5/2/2017.
 */

@Table(name = "PendingIntentAlarm", database = VolumizeDatabase.class)
public class PendingIntentAlarm extends BaseModel {

    @PrimaryKey (autoincrement = true)
    int id;

    @Column
    String eventId;

    @Column
    int day;

    public PendingIntentAlarm() {
        //Required empty constructor
    }

    public PendingIntentAlarm(String eventId, int day) {
        this.eventId = eventId;
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
