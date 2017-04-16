package com.cmpickle.volumize.view.schedule;

import com.cmpickle.volumize.view.BasePresenter;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/7/2017.
 */

public class SchedulePresenter extends BasePresenter<ScheduleView> {

    ScheduleView scheduleView;
    ScheduleRouter scheduleRouter;

    @Override
    protected void setView(ScheduleView scheduleView) {
        this.scheduleView = scheduleView;
    }

    public void setRouter(ScheduleRouter scheduleRouter) {
        this.scheduleRouter = scheduleRouter;
    }

    public void addScheduleClicked() {
        scheduleRouter.moveToEditSchedulePage();
    }
}
