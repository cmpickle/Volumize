package com.cmpickle.volumize.Inject;

import android.app.Application;

import com.cmpickle.volumize.Inject.module.AppModule;
import com.cmpickle.volumize.Inject.module.PresenterModule;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

public class Injector {

    private static VolumizeComponent volumizeComponent;

    private Injector() {

    }

    public static void init(Application application) {
        volumizeComponent = DaggerVolumizeComponent.builder()
                .appModule(new AppModule(application))
                .presenterModule(new PresenterModule())
                .build();
    }

    public static VolumizeComponent get() {
        return volumizeComponent;
    }
}
