package com.cmpickle.volumize.data.repositories;

import com.cmpickle.volumize.data.db.VolumizeDatabase;
import com.cmpickle.volumize.data.entity.ScheduleEvent;
import com.cmpickle.volumize.data.entity.ScheduleEvent_Table;
import com.raizlabs.android.dbflow.sql.queriable.StringQuery;

import java.util.List;

import javax.inject.Inject;

import static com.raizlabs.android.dbflow.sql.language.SQLite.select;

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
        return select().from(ScheduleEvent.class).queryList();
    }

    public ScheduleEvent findEventById(String eventId) {
        String sql = queryFindEventsById(eventId);
        return new StringQuery<>(ScheduleEvent.class, sql).querySingle();
    }

    public String queryFindEventsById(String eventId) {
        return select()
                .from(ScheduleEvent.class)
                .where(ScheduleEvent_Table.id.eq(eventId))
                .getQuery();
    }
}
