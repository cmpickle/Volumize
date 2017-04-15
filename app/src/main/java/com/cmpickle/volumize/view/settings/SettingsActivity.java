package com.cmpickle.volumize.view.settings;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.TopLevelActivity;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/7/2017.
 */

public class SettingsActivity extends TopLevelActivity {
    @Override
    protected Fragment createFragment() {
        return SettingsFragment.newInstance();
    }

    @Override
    public int getToolbarTitle() {
        return R.string.common_settings;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.menu_nav_settings;
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, SettingsActivity.class);
        activity.startActivity(intent);
    }
}
