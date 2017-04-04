package com.cmpickle.volumize.Inject;

import com.cmpickle.volumize.Inject.module.AppModule;
import com.cmpickle.volumize.VolumizeApp;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface VolumizeComponent {
    void inject(VolumizeApp volumizeApp);
}
