package com.cmpickle.volumize.data.repositories;

import com.cmpickle.volumize.data.db.VolumizeDatabase;
import com.cmpickle.volumize.data.entity.ScheduleEvent;
import com.cmpickle.volumize.data.entity.ScheduleEvent_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/18/2017.
 */

public class ScheduleEventRepository extends BaseRepository<VolumizeDatabase> {

    @Inject
    public ScheduleEventRepository(VolumizeDatabase database) {
        super(database);
    }

    public List<ScheduleEvent> findAll() {
        return SQLite.select().from(ScheduleEvent.class).queryList();
    }
}
