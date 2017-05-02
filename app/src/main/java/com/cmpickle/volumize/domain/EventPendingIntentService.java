package com.cmpickle.volumize.domain;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cmpickle.volumize.data.dto.ScheduleEventInfo;
import com.cmpickle.volumize.data.receivers.AlarmManagerBroadcastReceiver;
import com.cmpickle.volumize.view.util.DayUtil;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/30/2017.
 */

public class EventPendingIntentService {

    private final Context context;
    private final AlarmManager alarmManager;

    @Inject
    public EventPendingIntentService(Context context) {
        this.context = context;
        alarmManager =(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    void setEventPendingIntent(ScheduleEventInfo eventInfo) {
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        intent.setAction(AlarmManagerBroadcastReceiver.ACTION_CHANGE_VOLUME);
        Log.d("PI service", "ACTION " +intent.getAction());
        Log.d("PI service", "CATEGORIES " +intent.getCategories());
        Log.d("PI service", "DATA " +intent.getData());
        Log.d("PI service", "PACKAGE " +intent.getPackage());
        Log.d("PI service", "COMPONENT " +intent.getComponent());
        intent.putExtra(VolumeService.OPTION, eventInfo.getOption());
        intent.putExtra(VolumeService.AMOUNT, eventInfo.getAmount());
        intent.putExtra(VolumeService.VIBRATE, eventInfo.isVibrate());
        intent.putExtra(VolumeService.REPEAT_WEEKLY, eventInfo.isRepeatWeekly());
//        intent.putExtra(VolumeService.DAYS, eventInfo.getDays());
        intent.putExtra(VolumeService.ACTIVE, eventInfo.isActive());
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        DateTime dateTime = new DateTime();
        DateTime alarmTime = dateTime.withHourOfDay(eventInfo.getHour()).withMinuteOfHour(eventInfo.getMinute()).withSecondOfMinute(0).withMillisOfSecond(0);
        int days = eventInfo.getDays();
        if(DayUtil.isNone(days)) {
            if(alarmTime.isBeforeNow())
                alarmTime = alarmTime.plusWeeks(1);
            if(eventInfo.isRepeatWeekly()) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), DateTimeConstants.MILLIS_PER_WEEK, pi);
            } else {
                alarmManager.setWindow(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), 30000, pi);
            }
        }
        if(DayUtil.isSunday(days)) {
            alarmTime = alarmTime.withDayOfWeek(7);
            if(alarmTime.isBeforeNow())
                alarmTime = alarmTime.plusWeeks(1);
            if(eventInfo.isRepeatWeekly()) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), DateTimeConstants.MILLIS_PER_WEEK, pi);
            } else {
                alarmManager.setWindow(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), 30000, pi);
            }
        }
        if(DayUtil.isMonday(days)) {
            alarmTime = alarmTime.withDayOfWeek(1);
            if(alarmTime.isBeforeNow())
                alarmTime = alarmTime.plusWeeks(1);
            if(eventInfo.isRepeatWeekly()) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), DateTimeConstants.MILLIS_PER_WEEK, pi);
            } else {
                alarmManager.setWindow(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), 30000, pi);
            }
        }
        if(DayUtil.isTuesday(days)) {
            alarmTime = alarmTime.withDayOfWeek(2);
            if(alarmTime.isBeforeNow())
                alarmTime = alarmTime.plusWeeks(1);
            if(eventInfo.isRepeatWeekly()) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), DateTimeConstants.MILLIS_PER_WEEK, pi);
            } else {
                alarmManager.setWindow(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), 30000, pi);
            }
        }
        if(DayUtil.isWednesday(days)) {
            alarmTime = alarmTime.withDayOfWeek(3);
            if(alarmTime.isBeforeNow())
                alarmTime = alarmTime.plusWeeks(1);
            if(eventInfo.isRepeatWeekly()) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), DateTimeConstants.MILLIS_PER_WEEK, pi);
            } else {
                alarmManager.setWindow(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), 30000, pi);
            }
        }
        if(DayUtil.isThursday(days)) {
            alarmTime = alarmTime.withDayOfWeek(4);
            if(alarmTime.isBeforeNow())
                alarmTime = alarmTime.plusWeeks(1);
            if(eventInfo.isRepeatWeekly()) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), DateTimeConstants.MILLIS_PER_WEEK, pi);
            } else {
                alarmManager.setWindow(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), 30000, pi);
            }
        }
        if(DayUtil.isFriday(days)) {
            alarmTime = alarmTime.withDayOfWeek(5);
            if(alarmTime.isBeforeNow())
                alarmTime = alarmTime.plusWeeks(1);
            if(eventInfo.isRepeatWeekly()) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), DateTimeConstants.MILLIS_PER_WEEK, pi);
            } else {
                alarmManager.setWindow(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), 30000, pi);
            }
        }
        if(DayUtil.isSaturday(days)) {
            alarmTime = alarmTime.withDayOfWeek(6);
            if(alarmTime.isBeforeNow())
                alarmTime = alarmTime.plusWeeks(1);
            if(eventInfo.isRepeatWeekly()) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), DateTimeConstants.MILLIS_PER_WEEK, pi);
            } else {
                alarmManager.setWindow(AlarmManager.RTC_WAKEUP, alarmTime.getMillis(), 30000, pi);
            }
        }
        Log.d("EditSchedulePresenter", "hour:  " + eventInfo.getHour());
        Log.d("EditSchedulePresenter", "minute:  " + eventInfo.getMinute());
        Log.d("EditSchedulePresenter", "dateTime:  " + dateTime.getMillis());
        Log.d("EditSchedulePresenter", "alarmTime:  " + alarmTime.getMillis());
    }

    void deleteEventPendingIntent(ScheduleEventInfo eventInfo) {
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        intent.setAction(AlarmManagerBroadcastReceiver.ACTION_CHANGE_VOLUME);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();
    }

    boolean isEventPendingIntentActive(ScheduleEventInfo scheduleEventInfo) {
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        intent.setAction(AlarmManagerBroadcastReceiver.ACTION_CHANGE_VOLUME);
        return (PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_NO_CREATE) != null);
    }
}
