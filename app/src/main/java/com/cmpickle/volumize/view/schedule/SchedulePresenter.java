package com.cmpickle.volumize.view.schedule;

import com.cmpickle.volumize.data.dto.ScheduleEventInfo;
import com.cmpickle.volumize.data.entity.ScheduleEvent;
import com.cmpickle.volumize.data.repositories.ScheduleEventRepository;
import com.cmpickle.volumize.domain.ScheduleEventService;
import com.cmpickle.volumize.view.BasePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/7/2017.
 */

public class SchedulePresenter extends BasePresenter<ScheduleView> {

    ScheduleView scheduleView;
    ScheduleRouter scheduleRouter;
    ScheduleEventService scheduleEventService;

    ArrayList<ScheduleEvent> events = new ArrayList<>();

    @Inject
    public SchedulePresenter(ScheduleEventService scheduleEventService) {
        this.scheduleEventService = scheduleEventService;
    }

    @Override
    protected void setView(ScheduleView scheduleView) {
        this.scheduleView = scheduleView;
    }

    public void onResume() {
        events.clear();
        events.addAll(scheduleEventService.getAllEvents());
        updateAdapter();
    }

    public ArrayList<ScheduleEvent> getEvents() {
        return events;
    }

    public void updateAdapter() {
        scheduleView.updateAdapter();
    }

    public void setRouter(ScheduleRouter scheduleRouter) {
        this.scheduleRouter = scheduleRouter;
    }

    public void addScheduleClicked() {
        scheduleRouter.moveToEditSchedulePage();
    }

    public void onScheduleEventClicked(ScheduleEvent event) {
        scheduleRouter.moveToEditSchedulePage(event);
    }

    public void onScheduleEventChecked(ScheduleEvent event) {
        ScheduleEventInfo eventInfo = new ScheduleEventInfo(event);
        eventInfo.setActive(!eventInfo.isActive());
        scheduleEventService.saveEvent(eventInfo);
        onResume();
    }
}
