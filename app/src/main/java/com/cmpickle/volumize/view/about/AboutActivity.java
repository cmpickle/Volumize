package com.cmpickle.volumize.view.about;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.TopLevelActivity;
import com.cmpickle.volumize.view.about.recognitions.RecognitionsActivity;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/7/2017.
 */

public class AboutActivity extends TopLevelActivity implements AboutRouter {

    @Override
    protected Fragment createFragment() {
        return AboutFragment.newInstance();
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

    @Override
    public void moveToRateAppPage() {
        Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
        openURL.setData(Uri.parse("https://sites.google.com/site/cmpickle/"));
        startActivity(openURL);
    }

    @Override
    public void moveToRecognitoinsPage() {
        RecognitionsActivity.start(this);
    }

    @Override
    public void moveToHomepage() {
        Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
        openURL.setData(Uri.parse("https://sites.google.com/site/cmpickle/"));
        startActivity(openURL);
    }

    @Override
    public void moveToFeatureRequests() {
        Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
        openURL.setData(Uri.parse("https://sites.google.com/site/cmpickle/"));
        startActivity(openURL);
    }

    @Override
    public void moveToFeedback() {
        Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
        openURL.setData(Uri.parse("https://sites.google.com/site/cmpickle/"));
        startActivity(openURL);
    }
}
