package com.cmpickle.volumize;

import android.app.Application;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.data.db.VolumizeDatabase;
import com.cmpickle.volumize.data.db.platform.DatabaseInitializer;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

public class VolumizeApp extends Application {

    @Inject
    DatabaseInitializer databaseInitializer;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);

        Injector.init(this);
        Injector.get().inject(this);

        databaseInitializer.init(this, VolumizeDatabase.class);
    }
}
