package com.cmpickle.volumize.view.dialogs;

import com.cmpickle.volumize.data.dto.ScheduleEventInfo;
import com.cmpickle.volumize.domain.ScheduleEventService;
import com.cmpickle.volumize.view.BasePresenter;

import org.joda.time.DateTime;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 5/3/2017.
 */

public class VolumeRestorePresenter extends BasePresenter<VolumeRestoreView> {

    ScheduleEventService scheduleEventService;
    VolumeRestoreView view;

    @Inject
    public VolumeRestorePresenter(ScheduleEventService scheduleEventService) {
        this.scheduleEventService = scheduleEventService;
    }

    @Override
    protected void setView(VolumeRestoreView view) {
        this.view = view;
    }

    public void onRestoreClicked(int hour, int minute) {
        ScheduleEventInfo event = new ScheduleEventInfo();
        event.setOption(1);
        event.setAmount(12);
        event.setVibrate(true);
        event.setDays(0);
        DateTime dateTime = new DateTime();
        event.setHour(hour+dateTime.getHourOfDay());
        event.setMinute(minute+dateTime.getMinuteOfHour());
        event.setRepeatWeekly(false);
        event.setActive(true);
        scheduleEventService.saveEvent(event);
        //// TODO: 5/3/2017 This event should autodelete itself upon completion
    }
}
