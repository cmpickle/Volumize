package com.cmpickle.volumize.view.schedule.edit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.cmpickle.volumize.R;
import com.cmpickle.volumize.data.entity.ScheduleEvent;
import com.cmpickle.volumize.view.edit.EditActivity;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/9/2017.
 */

public class EditScheduleActivity extends EditActivity implements EditScheduleRouter {

    public static final String INTENT_EVENT_ID = "eventId";

    @Override
    protected Fragment createFragment() {
        return EditScheduleFragment.newInstance(getIntentId());
    }

    @Override
    protected int getToolbarTitleId() {
        return R.string.edit_schedule_title;
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, EditScheduleActivity.class);
        activity.startActivity(intent);
    }

    public static void startEditEvent(Activity activity, ScheduleEvent event) {
        Intent intent = new Intent(activity, EditScheduleActivity.class);
        intent.putExtra(INTENT_EVENT_ID, event.getId());
        activity.startActivity(intent);
    }

    @Override
    public void leave() {
       this.finish();
    }

    public Context getContext() {
        return this;
    }

    public String getIntentId() {
        return getIntent().getStringExtra(INTENT_EVENT_ID);
    }
}
