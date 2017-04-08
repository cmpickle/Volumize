package com.cmpickle.volumize.view;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/6/2017.
 */

public abstract class TopLevelPresenter extends BasePresenter {

    TopLevelRouter topLevelRouter;

    public void initialize() {

    }

    public void setRouter(TopLevelRouter topLevelRouter) {
        this.topLevelRouter = topLevelRouter;
    }

    public void onVolumesSelected() {
        topLevelRouter.moveToVolumePage();
    }

    public void onScheduleSelected() {

    }

    public void onProfileSelected() {

    }

    public void onSettingSelected() {

    }

    public void onAboutSelected() {

    }
}
