package com.cmpickle.volumize.data.repositories;

import com.cmpickle.volumize.data.db.platform.BaseDatabase;

import java.util.Observable;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

public class BaseRepository<T extends BaseDatabase> {

    private T database;

    public BaseRepository(T database) {
        this.database = database;
    }

    public void startTx() {
        database.startTx();
    }

    public void endTx(boolean isSuccess) {
        database.endTx(isSuccess);
    }

    public void doInTx(Runnable runnable) {
        boolean isSuccessful = true;
        startTx();
        try {
            runnable.run();
        } catch (Throwable t) {
            isSuccessful = false;
            throw t;
        } finally {
            endTx(isSuccessful);
        }
    }

//    public Observable<Void> doInRxTx(Runnable runnable) {
//
//    }
}
