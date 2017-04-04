package com.cmpickle.volumize.data.db;

import android.app.Application;

import com.cmpickle.volumize.data.db.platform.BaseDatabase;
import com.raizlabs.android.dbflow.annotation.Database;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

@Database(name = VolumizeDatabase.NAME, version = VolumizeDatabase.VERSION)
public class VolumizeDatabase extends BaseDatabase {
    public static final String NAME = "VolumizeDatabase";
    public static final int VERSION = 1;

    public VolumizeDatabase(Application application) {
        super(application);
    }

    @Override
    public String getDatabaseName() {
        return NAME;
    }
}
