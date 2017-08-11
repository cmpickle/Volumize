package com.cmpickle.volumize.domain;

import android.app.Application;
import android.preference.PreferenceManager;

import com.cmpickle.volumize.VolumizeApp;
import com.cmpickle.volumize.data.dto.ScheduleEventInfo;
import com.cmpickle.volumize.data.entity.ScheduleEvent;
import com.cmpickle.volumize.data.repositories.ScheduleEventRepository;
import com.cmpickle.volumize.util.preferences.Preferences;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/22/2017.
 */

public class ScheduleEventService {

    private final EventPendingIntentService eventPendingIntentService;
    public final ScheduleEventRepository eventRepository;
    Application volumizeApp;

    @Inject
    public ScheduleEventService(ScheduleEventRepository scheduleEventRepository, EventPendingIntentService eventPendingIntentService, Application volumizeApp) {
        this.eventPendingIntentService = eventPendingIntentService;
        this.eventRepository = scheduleEventRepository;
        this.volumizeApp = volumizeApp;
    }

    public void deleteEvent(String eventId) {
        ScheduleEvent event = eventRepository.findEventById(eventId);
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
        eventPendingIntentService.setEventPendingIntent(eventInfo, false);
        scheduleEvent.save();
    }

    public void saveTempEvent(ScheduleEventInfo eventInfo) {
        ScheduleEvent scheduleEvent;
        if(eventInfo.getId() == null) {
            scheduleEvent = eventInfo.getScheduleEvent();
        } else {
            scheduleEvent = eventRepository.findEventById(eventInfo.getId());
            eventInfo.update(scheduleEvent);
        }
        eventPendingIntentService.setEventPendingIntent(eventInfo, true);
        scheduleEvent.save();
    }

    public ScheduleEventInfo getEventInfo(String eventId) {
        ScheduleEvent event = eventRepository.findEventById(eventId);
        return new ScheduleEventInfo(event);
    }

    public List<ScheduleEvent> getAllEvents() {
        List<ScheduleEvent> events;
        Preferences preferences = new Preferences(PreferenceManager.getDefaultSharedPreferences(volumizeApp));
        if (preferences.getSortType().equals("timeofday")) {
            events = eventRepository.findAllSortTime();
        } else {
            events = eventRepository.findAll();
        }
        return events;
    }
}
