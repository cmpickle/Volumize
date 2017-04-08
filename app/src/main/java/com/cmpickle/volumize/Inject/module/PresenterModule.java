package com.cmpickle.volumize.Inject.module;

import com.cmpickle.volumize.view.schedule.SchedulePresenter;
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
}
