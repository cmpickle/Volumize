package com.cmpickle.volumize.domain;

import com.cmpickle.volumize.data.repositories.ScheduleEventRepository;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 5/7/2017.
 */

public class RestoreHelper {
    private ScheduleEventService eventService;
    private ScheduleEventRepository eventRepository;

    @Inject
    public RestoreHelper(ScheduleEventService scheduleEventService, ScheduleEventRepository scheduleEventRepository) {
        eventService = scheduleEventService;
        eventRepository = scheduleEventRepository;
    }

    public ScheduleEventService getEventService() {
        return eventService;
    }

    public ScheduleEventRepository getEventRepository() {
        return eventRepository;
    }
}
