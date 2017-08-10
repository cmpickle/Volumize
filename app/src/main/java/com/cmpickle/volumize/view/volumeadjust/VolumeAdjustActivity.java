package com.cmpickle.volumize.view.volumeadjust;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.TopLevelActivity;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

public class VolumeAdjustActivity extends TopLevelActivity implements VolumeAdjustRouter {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getToolbarTitle() {
        return R.string.app_name;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.menu_nav_volumes;
    }

    @Override
    protected Fragment createFragment() {
        return new VolumeAdjustFragment();
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, VolumeAdjustActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
