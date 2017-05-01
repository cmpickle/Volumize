package com.cmpickle.volumize.domain;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cmpickle.volumize.data.dto.ScheduleEventInfo;
import com.cmpickle.volumize.data.entity.ScheduleEvent;
import com.cmpickle.volumize.data.receivers.AlarmManagerBroadcastReceiver;
import com.cmpickle.volumize.data.repositories.ScheduleEventRepository;

import org.joda.time.DateTime;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/22/2017.
 */

public class ScheduleEventService {

    private final EventPendingIntentService eventPendingIntentService;
    public final ScheduleEventRepository eventRepository;

    @Inject
    public ScheduleEventService(ScheduleEventRepository scheduleEventRepository, EventPendingIntentService eventPendingIntentService) {
        this.eventPendingIntentService = eventPendingIntentService;
        this.eventRepository = scheduleEventRepository;
    }

    public void deleteEvent(String eventId) {
        ScheduleEvent event = eventRepository.findEventById(eventId);
        //todo: delete the pending intent
        eventPendingIntentService.deleteEventPendingIntent(new ScheduleEventInfo(event));

        event.delete();
    }

    public void saveEvent(ScheduleEventInfo eventInfo) {
        ScheduleEvent scheduleEvent;
        if(eventInfo.getId() == null) {
            scheduleEvent = eventInfo.getScheduleEvent();
        } else {
            scheduleEvent = eventRepository.findEventById(eventInfo.getId());
            eventInfo.update(scheduleEvent);
        }
        eventPendingIntentService.setEventPendingIntent(eventInfo);
        scheduleEvent.save();
    }

    public ScheduleEventInfo getEventInfo(String eventId) {
        ScheduleEvent event = eventRepository.findEventById(eventId);
        ScheduleEventInfo scheduleEventInfo = new ScheduleEventInfo(event);
        return scheduleEventInfo;
    }

    public List<ScheduleEvent> getAllEvents() {
        return eventRepository.findAll();
    }
}
