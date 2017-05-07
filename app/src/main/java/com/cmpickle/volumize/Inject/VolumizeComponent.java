package com.cmpickle.volumize.Inject;

import com.cmpickle.volumize.Inject.module.AppModule;
import com.cmpickle.volumize.Inject.module.PresenterModule;
import com.cmpickle.volumize.VolumizeApp;
import com.cmpickle.volumize.domain.RestoreEventsService;
import com.cmpickle.volumize.view.TopLevelActivity;
import com.cmpickle.volumize.view.about.AboutFragment;
import com.cmpickle.volumize.view.about.recognitions.RecognitionsFragment;
import com.cmpickle.volumize.view.dialogs.VolumeRestoreDialog;
import com.cmpickle.volumize.view.profile.ProfileFragment;
import com.cmpickle.volumize.view.profile.edit.EditProfileFragment;
import com.cmpickle.volumize.view.schedule.ScheduleFragment;
import com.cmpickle.volumize.view.schedule.edit.EditScheduleFragment;
import com.cmpickle.volumize.view.settings.SettingsFragment;
import com.cmpickle.volumize.view.volumeadjust.VolumeAdjustFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

@Singleton
@Component(modules = {
        AppModule.class,
        PresenterModule.class})
public interface VolumizeComponent {
    void inject(VolumizeApp volumizeApp);

    void inject(TopLevelActivity topLevelActivity);

    void inject(VolumeAdjustFragment volumeAdjustFragment);

    void inject(ScheduleFragment scheduleFragment);

    void inject(EditScheduleFragment editScheduleFragment);

    void inject(ProfileFragment profileFragment);

    void inject(EditProfileFragment editProfileFragment);

    void inject(SettingsFragment settingsFragment);

    void inject(AboutFragment aboutFragment);

    void inject(RecognitionsFragment recognitionsFragment);

    void inject(VolumeRestoreDialog volumeRestoreDialog);

    void inject(RestoreEventsService restoreEventsService);
}
