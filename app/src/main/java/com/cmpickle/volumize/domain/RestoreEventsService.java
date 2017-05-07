package com.cmpickle.volumize.domain;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.data.dto.ScheduleEventInfo;
import com.cmpickle.volumize.data.entity.ScheduleEvent;

import java.util.ArrayList;

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
        ArrayList<ScheduleEvent> events = new ArrayList<>();
        events.addAll(helper.getEventRepository().findAll());
        for(int i=0; i<events.size(); ++i) {
            helper.getEventService().saveEvent(new ScheduleEventInfo(events.get(i)));
        }
    }
}
