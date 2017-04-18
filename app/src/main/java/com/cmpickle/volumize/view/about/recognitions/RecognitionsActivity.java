package com.cmpickle.volumize.view.about.recognitions;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.cmpickle.volumize.view.BaseActivity;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/17/2017.
 */

public class RecognitionsActivity extends BaseActivity {
    @Override
    protected Fragment createFragment() {
        return new RecognitionsFragment();
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, RecognitionsActivity.class);
        activity.startActivity(intent);
    }
}
