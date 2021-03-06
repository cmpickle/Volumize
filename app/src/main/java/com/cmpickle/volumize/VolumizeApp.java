package com.cmpickle.volumize;

import android.app.Application;
import android.preference.PreferenceManager;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.data.db.VolumizeDatabase;
import com.cmpickle.volumize.data.db.platform.DatabaseInitializer;
import com.cmpickle.volumize.util.preferences.Preferences;
import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.google.android.gms.ads.MobileAds;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import io.fabric.sdk.android.*;

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

        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);

        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

        if(BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return;
            }
            LeakCanary.install(this);
        }

        MobileAds.initialize(this, "ca-app-pub-9007760081684097~4346496568");

        Injector.init(this);
        Injector.get().inject(this);

        databaseInitializer.init(this, VolumizeDatabase.class);

        Preferences preferences = new Preferences(PreferenceManager.getDefaultSharedPreferences(this));
        preferences.init();
    }
}
