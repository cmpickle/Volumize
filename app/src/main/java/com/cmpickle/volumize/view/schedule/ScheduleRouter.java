package com.cmpickle.volumize.view.schedule;

import com.cmpickle.volumize.data.entity.ScheduleEvent;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/9/2017.
 */

public interface ScheduleRouter {
    void moveToEditSchedulePage();

    void moveToEditSchedulePage(ScheduleEvent event);
}
