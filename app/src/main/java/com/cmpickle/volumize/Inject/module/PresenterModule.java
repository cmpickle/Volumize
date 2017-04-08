package com.cmpickle.volumize.Inject.module;

import com.cmpickle.volumize.view.about.AboutPresenter;
import com.cmpickle.volumize.view.profile.ProfilePresenter;
import com.cmpickle.volumize.view.schedule.SchedulePresenter;
import com.cmpickle.volumize.view.settings.SettingsPresenter;
import com.cmpickle.volumize.view.volumeadjust.VolumeAdjustPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/6/2017.
 */

@Module
public class PresenterModule {

    @Provides
    public VolumeAdjustPresenter provideVolumeAdjustPresenter() {
        return new VolumeAdjustPresenter();
    }

    @Provides
    public SchedulePresenter providesSchedulePresenter() {
        return new SchedulePresenter();
    }

    @Provides
    public ProfilePresenter providesProfilePresenter() {
        return new ProfilePresenter();
    }

    @Provides
    public SettingsPresenter providesSettingsPresenter() {
        return new SettingsPresenter();
    }

    @Provides
    public AboutPresenter providesAboutPresenter() {
        return new AboutPresenter();
    }
}
