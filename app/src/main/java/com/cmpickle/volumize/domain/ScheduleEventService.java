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

    public final ScheduleEventRepository eventRepository;

    @Inject
    public ScheduleEventService(ScheduleEventRepository scheduleEventRepository) {
        this.eventRepository = scheduleEventRepository;
    }

    public void deleteEvent(String eventId) {
        ScheduleEvent event = eventRepository.findEventById(eventId);
        //todo: delete the pending intent
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
