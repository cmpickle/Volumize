package com.cmpickle.volumize.data.db.platform;

import android.app.Application;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

public abstract class BaseDatabase {

    protected Application application;

    public BaseDatabase(Application application) {
        this.application = application;
    }

    /**
     *  useful for starting transactions anywhere
     */
    public void startTx() {
        getWritableDatabase().beginTransaction();
    }

    /**
     *  useful for ending transactions anywhere
     * @param isSuccess whether to commit or roll back
     */
    public boolean endTx(boolean isSuccess) {
        DatabaseWrapper db = getWritableDatabase();
        if(isSuccess) {
            db.setTransactionSuccessful();
        }
        db.endTransaction();
        return isSuccess;
    }

    public DatabaseWrapper getWritableDatabase() {
        return getDefinition().getWritableDatabase();
    }

    public DatabaseDefinition getDefinition() {
        return FlowManager.getDatabase(getDatabaseName());
    }

    public abstract String getDatabaseName();
}
