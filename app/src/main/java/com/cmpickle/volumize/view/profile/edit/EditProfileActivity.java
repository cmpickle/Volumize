package com.cmpickle.volumize.view.profile.edit;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.edit.EditActivity;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/16/2017.
 */

public class EditProfileActivity extends EditActivity implements EditProfileRouter {
    @Override
    protected int getToolbarTitleId() {
        return R.string.edit_profile;
    }

    @Override
    protected Fragment createFragment() {
        return new EditProfileFragment();
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, EditProfileActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void leave() {
        finish();
    }
}
