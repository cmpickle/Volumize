package com.cmpickle.volumize.Inject.module;

import com.cmpickle.volumize.domain.VolumeService;
import com.cmpickle.volumize.view.about.AboutPresenter;
import com.cmpickle.volumize.view.about.recognitions.RecognitionsPresenter;
import com.cmpickle.volumize.view.profile.ProfilePresenter;
import com.cmpickle.volumize.view.profile.edit.EditProfilePresenter;
import com.cmpickle.volumize.view.schedule.SchedulePresenter;
import com.cmpickle.volumize.view.schedule.edit.EditSchedulePresenter;
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

//    @Provides
//    public VolumeAdjustPresenter provideVolumeAdjustPresenter() {
//        return new VolumeAdjustPresenter(VolumeService);
//    }

    @Provides
    public SchedulePresenter providesSchedulePresenter() {
        return new SchedulePresenter();
    }

    @Provides
    public EditSchedulePresenter providesEditSchedulePresenter() {
        return new EditSchedulePresenter();
    }

    @Provides
    public ProfilePresenter providesProfilePresenter() {
        return new ProfilePresenter();
    }

    @Provides
    public EditProfilePresenter providesEditProfilePresenter() {
        return new EditProfilePresenter();
    }

    @Provides
    public SettingsPresenter providesSettingsPresenter() {
        return new SettingsPresenter();
    }

    @Provides
    public AboutPresenter providesAboutPresenter() {
        return new AboutPresenter();
    }

    @Provides
    public RecognitionsPresenter providesRecognitionsPresenter() {
        return new RecognitionsPresenter();
    }
}
