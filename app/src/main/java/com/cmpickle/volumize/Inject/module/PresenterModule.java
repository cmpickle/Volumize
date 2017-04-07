package com.cmpickle.volumize.Inject.module;

import com.cmpickle.volumize.view.TopLevelPresenter;
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
    public TopLevelPresenter provideTopLevelPresenter() {
        return new TopLevelPresenter() {

            @Override
            protected void setView(Object o) {

            }
        };
    }

    @Provides
    public VolumeAdjustPresenter provideVolumeAdjustPresenter() {
        return new VolumeAdjustPresenter();
    }
}
