package com.cmpickle.volumize.view.schedule.edit;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cmpickle.volumize.data.dto.ScheduleEventInfo;
import com.cmpickle.volumize.data.receivers.AlarmManagerBroadcastReceiver;
import com.cmpickle.volumize.domain.ScheduleEventService;
import com.cmpickle.volumize.domain.VolumeService;
import com.cmpickle.volumize.view.edit.EditPresenter;
import com.cmpickle.volumize.view.util.DayUtil;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import icepick.State;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/9/2017.
 */

public class EditSchedulePresenter extends EditPresenter {

    private final ScheduleEventService eventService;

    @State
    ScheduleEventInfo oldEvent;

    @State
    ScheduleEventInfo newEvent;

    EditScheduleView editScheduleView;
    EditScheduleRouter editScheduleRouter;

    int hour = 7;
    int minute = 0;

    @Inject
    public EditSchedulePresenter(ScheduleEventService scheduleEventService) {
        this.eventService = scheduleEventService;
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
        eventService.saveEvent(newEvent);
//        ScheduleEvent scheduleEvent = new ScheduleEvent();
//        scheduleEvent.setOption(editScheduleView.getOption());
//        scheduleEvent.setAmount(editScheduleView.getAmount());
//        scheduleEvent.setVibrate(editScheduleView.isVibrate());
//        scheduleEvent.setRepeatWeekly(editScheduleView.isRepeatWeekly());
//        scheduleEvent.setDays(editScheduleView.getDays());
//        scheduleEvent.setHour(hour);
//        scheduleEvent.setMinute(minute);
//        scheduleEvent.setActive(true);
//        scheduleEvent.save();

        AlarmManager alarmManager = (AlarmManager) editScheduleRouter.getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(editScheduleRouter.getContext(), AlarmManagerBroadcastReceiver.class);
        intent.putExtra(VolumeService.OPTION, newEvent.getOption());
        intent.putExtra(VolumeService.AMOUNT, newEvent.getAmount());
        intent.putExtra(VolumeService.VIBRATE, newEvent.isVibrate());
        intent.putExtra(VolumeService.REPEAT_WEEKLY, newEvent.isRepeatWeekly());
        intent.putExtra(VolumeService.DAYS, newEvent.getDays());
        intent.putExtra(VolumeService.ACTIVE, newEvent.isActive());
        PendingIntent pi = PendingIntent.getBroadcast(editScheduleRouter.getContext(), 0, intent, 0);
        DateTime dateTime = new DateTime();
        Log.d("EditSchedulePresenter", "hour:  " + newEvent.getHour());
        Log.d("EditSchedulePresenter", "minute:  " + newEvent.getMinute());
        DateTime alarmTime = dateTime.withHourOfDay(newEvent.getHour()).withMinuteOfHour(newEvent.getMinute()).withSecondOfMinute(0).withMillisOfSecond(0);
        Log.d("EditSchedulePresenter", "dateTime:  " + dateTime.getMillis());
        Log.d("EditSchedulePresenter", "alarmTime:  " + alarmTime.getMillis());
        if(newEvent.isRepeatWeekly()) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), 604800000, pi);
        } else {
            alarmManager.setWindow(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), 30000, pi);
        }

        editScheduleRouter.leave();
    }

    public void onOptionChanged(int option) {
        if(newEvent != null) {
            newEvent.setOption(option);
        }
    }

    public void onVolumeChanged(int amount) {
        if(newEvent != null) {
            newEvent.setAmount(amount);
        }
    }

    public void onVibrateChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setVibrate(checked);
        }
    }

    public void onSundayChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setDays(DayUtil.setFlagSunday(checked, newEvent.getDays()));
        }
    }

    public void onMondayChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setDays(DayUtil.setFlagMonday(checked, newEvent.getDays()));
        }
    }

    public void onTuesdayChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setDays(DayUtil.setFlagTuesday(checked, newEvent.getDays()));
        }
    }

    public void onWednesdayChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setDays(DayUtil.setFlagWednesday(checked, newEvent.getDays()));
        }
    }

    public void onThursdayChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setDays(DayUtil.setFlagThursday(checked, newEvent.getDays()));
        }
    }

    public void onFridayChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setDays(DayUtil.setFlagFriday(checked, newEvent.getDays()));
        }
    }

    public void onSaturdayChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setDays(DayUtil.setFlagSaturday(checked, newEvent.getDays()));
        }
    }

    public void onTimeChanged(int hour, int minute) {
        if(newEvent != null) {
            this.hour = hour;
            this.minute = minute;
            newEvent.setHour(hour);
            newEvent.setMinute(minute);
        }
    }

    public void onRepeatWeeklyChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setRepeatWeekly(checked);
        }
    }

    public void onViewCreated() {
        if(newEvent != null) {
            if(editScheduleView.isEditMode()) {
                editScheduleView.showDeleteTextView();
            }
        } else {
            if(editScheduleView.isEditMode()) {
                loadEvent();
            } else {
                oldEvent = new ScheduleEventInfo();
                editScheduleView.bindEventOnly(oldEvent);
                if (editScheduleView.getEventId() == null) {
                    newEvent = new ScheduleEventInfo(oldEvent);
                }
            }
        }
        onEnteredData();
    }

    private void loadEvent() {
        oldEvent = eventService.getEventInfo(editScheduleView.getEventId());
        editScheduleView.bindEventOnly(oldEvent);
        newEvent = new ScheduleEventInfo(oldEvent);

        editScheduleView.showDeleteTextView();
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

    public void onDeleteClicked() {
        editScheduleView.displayDeleteConfirmation();
    }

    @Override
    public void onDeleteConfirmed() {
        eventService.deleteEvent(oldEvent.getId());
        editScheduleRouter.leave();
    }
}
