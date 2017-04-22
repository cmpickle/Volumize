package com.cmpickle.volumize.view.schedule.edit;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cmpickle.volumize.data.entity.ScheduleEvent;
import com.cmpickle.volumize.data.receivers.AlarmManagerBroadcastReceiver;
import com.cmpickle.volumize.domain.VolumeService;
import com.cmpickle.volumize.view.edit.EditPresenter;

import org.joda.time.DateTime;

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

        AlarmManager alarmManager = (AlarmManager) editScheduleRouter.getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(editScheduleRouter.getContext(), AlarmManagerBroadcastReceiver.class);
        intent.putExtra(VolumeService.OPTION, scheduleEvent.getOption());
        intent.putExtra(VolumeService.AMOUNT, scheduleEvent.getAmount());
        intent.putExtra(VolumeService.VIBRATE, scheduleEvent.isVibrate());
        intent.putExtra(VolumeService.REPEAT_WEEKLY, Boolean.TRUE);
        intent.putExtra(VolumeService.DAYS, scheduleEvent.getDays());
        intent.putExtra(VolumeService.ACTIVE, scheduleEvent.isActive());
        PendingIntent pi = PendingIntent.getBroadcast(editScheduleRouter.getContext(), 0, intent, 0);
        DateTime dateTime = new DateTime();
        Log.d("EditSchedulePresenter", "hour:  " + scheduleEvent.getHour());
        Log.d("EditSchedulePresenter", "minute:  " + scheduleEvent.getMinute());
        DateTime alarmTime = dateTime.withHourOfDay(scheduleEvent.getHour()).withMinuteOfHour(scheduleEvent.getMinute()).withSecondOfMinute(0).withMillisOfSecond(0);
        Log.d("EditSchedulePresenter", "dateTime:  " + dateTime.getMillis());
        Log.d("EditSchedulePresenter", "alarmTime:  " + alarmTime.getMillis());
        if(scheduleEvent.isRepeatWeekly()) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), 604800000, pi);
        } else {
            alarmManager.setWindow(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), 30000, pi);
        }

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
