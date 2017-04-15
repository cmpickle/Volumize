package com.cmpickle.volumize.view.about;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.TopLevelActivity;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/7/2017.
 */

public class AboutActivity extends TopLevelActivity {
    @Override
    protected Fragment createFragment() {
        return new AboutFragment();
    }

    @Override
    public int getToolbarTitle() {
        return R.string.common_about;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.menu_nav_about;
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, AboutActivity.class);
        activity.startActivity(intent);
    }
}
