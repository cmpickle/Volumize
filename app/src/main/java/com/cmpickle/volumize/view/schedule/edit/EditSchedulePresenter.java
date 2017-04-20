package com.cmpickle.volumize.view.schedule.edit;


import com.cmpickle.volumize.data.entity.ScheduleEvent;
import com.cmpickle.volumize.view.edit.EditPresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/9/2017.
 */

public class EditSchedulePresenter extends EditPresenter {

    EditScheduleView editScheduleView;
    EditScheduleRouter editScheduleRouter;

    int hour = 7;
    int minute = 0;

    public EditSchedulePresenter() {
    }

    public void setView(EditScheduleView view) {
        super.setView(view);
        this.editScheduleView = view;
    }

    public void onViewResumed() {
        onRepeatWeeklySwitched();
        onMuteSwitched();
    }

    public void setScheduleRouter(EditScheduleRouter editScheduleRouter) {
        super.setEditRouter(editScheduleRouter);
        this.editScheduleRouter = editScheduleRouter;
    }

    @Override
    public void onAttemptSave() {
        //save event to database
        ScheduleEvent scheduleEvent = new ScheduleEvent();
        scheduleEvent.setOption(editScheduleView.getOption());
        scheduleEvent.setAmount(editScheduleView.getAmount());
        scheduleEvent.setVibrate(editScheduleView.isVibrate());
        scheduleEvent.setRepeatWeekly(editScheduleView.isRepeatWeekly());
        scheduleEvent.setDays(editScheduleView.getDays());
        scheduleEvent.setHour(hour);
        scheduleEvent.setMinute(minute);
        scheduleEvent.setActive(true);
        scheduleEvent.save();

        editScheduleRouter.leave();
    }

    public void onViewCreated() {
        onEnteredData();
    }

    public void onTimePickerClicked() {
        editScheduleView.openTimePicker();
    }

    public void onTimePicked(int hourOfDay, int minute) {
        hour = hourOfDay;
        this.minute = minute;
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        String formattedDate = format.format(calendar.getTime());
        editScheduleView.onTimePicked(formattedDate);
    }

    public void onRepeatWeeklySwitched() {
    }

    public void onMuteSwitched() {
        editScheduleView.updateMuteView();
    }

    public void onSeekBarMoved(int progress) {
        editScheduleView.setVolumeTV(String.valueOf(progress));
    }
}
