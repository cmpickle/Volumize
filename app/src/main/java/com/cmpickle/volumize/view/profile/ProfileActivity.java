package com.cmpickle.volumize.view.profile;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.TopLevelActivity;
import com.cmpickle.volumize.view.profile.edit.EditProfileActivity;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/7/2017.
 */

public class ProfileActivity extends TopLevelActivity implements ProfileRouter {
    @Override
    protected Fragment createFragment() {
        return new ProfileFragment();
    }

    @Override
    public int getToolbarTitle() {
        return R.string.common_profiles;
    }

    @Override
    protected int getNavigationMenuItemId() {
//        return R.id.menu_nav_profiles;
        return 0;
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ProfileActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void moveToEditProfilePage() {
        EditProfileActivity.start(this);
    }
}
