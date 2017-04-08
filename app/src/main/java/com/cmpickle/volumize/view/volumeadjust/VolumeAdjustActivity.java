package com.cmpickle.volumize.view.volumeadjust;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.BaseActivity;
import com.cmpickle.volumize.view.TopLevelActivity;

import butterknife.BindView;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

public class VolumeAdjustActivity extends TopLevelActivity {

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
        return 0;
    }

    @Override
    protected Fragment createFragment() {
        return new VolumeAdjustFragment();
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, VolumeAdjustActivity.class);
        activity.startActivity(intent);
    }
}
