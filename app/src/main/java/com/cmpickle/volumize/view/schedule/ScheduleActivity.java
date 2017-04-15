package com.cmpickle.volumize.view.schedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.TopLevelActivity;
import com.cmpickle.volumize.view.schedule.edit.EditScheduleActivity;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/7/2017.
 */

public class ScheduleActivity extends TopLevelActivity implements ScheduleRouter, ScheduleView {

    @Override
    protected Fragment createFragment() {
        return new ScheduleFragment();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getToolbarTitle() {
        return R.string.common_schedule;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.menu_nav_schedule;
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ScheduleActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void moveToEditSchedulePage() {
        EditScheduleActivity.start(this);
    }
}
