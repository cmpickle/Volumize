package com.cmpickle.volumize.data.repositories;

import com.cmpickle.volumize.data.db.VolumizeDatabase;
import com.cmpickle.volumize.data.entity.PendingIntentAlarm;
import com.cmpickle.volumize.data.entity.PendingIntentAlarm_Table;

import java.util.List;

import javax.inject.Inject;

import static com.raizlabs.android.dbflow.sql.language.SQLite.select;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 5/2/2017.
 */

public class PendingIntentAlarmRepository extends BaseRepository<VolumizeDatabase> {

    @Inject
    public PendingIntentAlarmRepository(VolumizeDatabase database) {
        super(database);
    }

    public List<PendingIntentAlarm> findAll() {
        return select().from(PendingIntentAlarm.class).queryList();
    }

    public List<PendingIntentAlarm> findAlarmsByEventId(String eventId) {
        return select().from(PendingIntentAlarm.class).where(PendingIntentAlarm_Table.eventId.eq(eventId)).queryList();
    }

    public PendingIntentAlarm findAlarmByEventIdAndDay(String eventId, int day) {
        return select().from(PendingIntentAlarm.class).where(PendingIntentAlarm_Table.eventId.eq(eventId)).and(PendingIntentAlarm_Table.day.eq(day)).querySingle();
    }
}
