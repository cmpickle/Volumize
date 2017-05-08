package com.cmpickle.volumize.domain;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.annimon.stream.Stream;
import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.data.dto.ScheduleEventInfo;
import com.cmpickle.volumize.data.entity.ScheduleEvent;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 5/7/2017.
 */

public class RestoreEventsService extends IntentService {

    @Inject
    RestoreHelper helper;

    public RestoreEventsService() {
        super("RestoreEventService");
        Injector.get().inject(this);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        List<ScheduleEvent> events = helper.getEventRepository().findAll();
        Stream.of(events).forEach(event -> helper.getEventService().saveEvent(new ScheduleEventInfo(event)));
    }
}
