package com.cmpickle.volumize.Inject;

import com.cmpickle.volumize.Inject.module.AppModule;
import com.cmpickle.volumize.Inject.module.PresenterModule;
import com.cmpickle.volumize.VolumizeApp;
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

    void inject(VolumeAdjustFragment volumeAdjustFragment);
}
