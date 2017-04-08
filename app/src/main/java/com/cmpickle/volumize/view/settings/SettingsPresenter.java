package com.cmpickle.volumize.view.settings;


import com.cmpickle.volumize.view.BasePresenter;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/7/2017.
 */

public class SettingsPresenter extends BasePresenter<SettingsView> {

    SettingsView settingsView;

    @Override
    protected void setView(SettingsView settingsView) {
        this.settingsView = settingsView;
    }
}
