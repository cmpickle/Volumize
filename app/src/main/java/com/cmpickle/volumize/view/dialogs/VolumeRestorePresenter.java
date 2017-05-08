package com.cmpickle.volumize.view.dialogs;

import com.cmpickle.volumize.data.dto.ScheduleEventInfo;
import com.cmpickle.volumize.domain.ScheduleEventService;
import com.cmpickle.volumize.domain.VolumeService;
import com.cmpickle.volumize.view.BasePresenter;

import org.joda.time.DateTime;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 5/3/2017.
 */

public class VolumeRestorePresenter extends BasePresenter<VolumeRestoreView> {

    ScheduleEventService scheduleEventService;
    VolumeService volumeService;
    VolumeRestoreView view;

    @Inject
    public VolumeRestorePresenter(ScheduleEventService scheduleEventService, VolumeService volumeService) {
        this.scheduleEventService = scheduleEventService;
        this.volumeService = volumeService;
    }

    @Override
    protected void setView(VolumeRestoreView view) {
        this.view = view;
    }

    public void onCreate() {
        view.setMaxVolumeRestoreSeekBar(volumeService.getRingToneMaxVolume());
    }

    public void onRestoreClicked(int hour, int minute, int level) {
        ScheduleEventInfo event = new ScheduleEventInfo();
        event.setOption(1);
        event.setAmount(level);
        event.setVibrate(true);
        event.setDays(0);
        DateTime dateTime = new DateTime();
        event.setHour(hour+dateTime.getHourOfDay());
        event.setMinute(minute+dateTime.getMinuteOfHour());
        event.setRepeatWeekly(false);
        event.setActive(true);
        scheduleEventService.saveTempEvent(event);
    }
}
