package com.cmpickle.volumize.Inject.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application providesApplication() {
        return application;
    }
}
