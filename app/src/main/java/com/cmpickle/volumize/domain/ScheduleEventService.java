package com.cmpickle.volumize.domain;

import com.cmpickle.volumize.data.dto.ScheduleEventInfo;
import com.cmpickle.volumize.data.entity.ScheduleEvent;
import com.cmpickle.volumize.data.repositories.ScheduleEventRepository;

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
        return eventRepository.findAll();
    }
}
