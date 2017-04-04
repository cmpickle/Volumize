package com.cmpickle.volumize.data.db.platform;

import android.app.Application;

import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

@Singleton
public class DatabaseInitializer {

    @Inject
    public DatabaseInitializer() {

    }

    public final void init(Application application, Class<? extends BaseDatabase>... databaseTypes) {
        System.loadLibrary("sqliteX");
        FlowConfig.Builder flowConfigBuilder = new FlowConfig.Builder(application);
        for(Class<? extends BaseDatabase> databaseType : databaseTypes) {
            DatabaseConfig databaseConfig = new DatabaseConfig.Builder(databaseType)
                    .build();
            flowConfigBuilder.addDatabaseConfig(databaseConfig);
        }
        FlowConfig flowConfig = flowConfigBuilder.openDatabasesOnInit(true).build();
        FlowManager.init(flowConfig);
    }
}
