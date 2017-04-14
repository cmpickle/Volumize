package com.cmpickle.volumize.view;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/6/2017.
 */

public class TopLevelPresenter extends BasePresenter<TopLevelView> {

    TopLevelView topLevelView;

    TopLevelRouter topLevelRouter;

    @Inject
    public TopLevelPresenter() {

    }

    @Override
    protected void setView(TopLevelView topLevelView) {
        this.topLevelView = topLevelView;
    }

    public void setRouter(TopLevelRouter topLevelRouter) {
        this.topLevelRouter = topLevelRouter;
    }

    public void initialize() {

    }

    public void onVolumesSelected() {
        topLevelRouter.moveToVolumePage();
    }

    public void onScheduleSelected() {
        topLevelRouter.moveToSchedulePage();
    }

    public void onProfileSelected() {
        topLevelRouter.moveToProfilePage();
    }

    public void onSettingSelected() {
        topLevelRouter.moveToSettingsPage();
    }

    public void onAboutSelected() {
        topLevelRouter.moveToAboutPage();
    }
}
