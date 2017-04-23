package com.cmpickle.volumize.view.schedule;

import com.cmpickle.volumize.data.entity.ScheduleEvent;
import com.cmpickle.volumize.data.repositories.ScheduleEventRepository;
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
    ScheduleEventRepository repository;

    ArrayList<ScheduleEvent> events = new ArrayList<>();

    @Inject
    public SchedulePresenter(ScheduleEventRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void setView(ScheduleView scheduleView) {
        this.scheduleView = scheduleView;
    }

    public void onResume() {
        events.clear();
        events.addAll(repository.findAll());
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
}
