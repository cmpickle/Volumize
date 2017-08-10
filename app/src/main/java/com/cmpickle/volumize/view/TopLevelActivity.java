package com.cmpickle.volumize.view;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.about.AboutActivity;
import com.cmpickle.volumize.view.profile.ProfileActivity;
import com.cmpickle.volumize.view.schedule.ScheduleActivity;
import com.cmpickle.volumize.view.settings.SettingsActivity;
import com.cmpickle.volumize.view.volumeadjust.VolumeAdjustActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/6/2017.
 */

public abstract class TopLevelActivity extends BaseActivity implements TopLevelRouter, TopLevelView {

    @Inject
    TopLevelPresenter topLevelPresenter;

    @BindView(R.id.navigation_menu)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout navigationDrawerLayout;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    private Unbinder unbinder;

    public TopLevelActivity() {
        Injector.get().inject(this);
    }

    @Override
    public void onBackPressed() {
        if(navigationDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            navigationDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            handleUpArrowSelected();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void moveToVolumePage() {
        VolumeAdjustActivity.start(this);
    }

    @Override
    public void moveToSchedulePage() {
        ScheduleActivity.start(this);
    }

    @Override
    public void moveToProfilePage() {
        ProfileActivity.start(this);
    }

    @Override
    public void moveToSettingsPage() {
        SettingsActivity.start(this);
    }

    @Override
    public void moveToAboutPage() {
        AboutActivity.start(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        unbinder = ButterKnife.bind(this);

        topLevelPresenter.setView(this);
        topLevelPresenter.setRouter(this);

        toolbar.setTitle(getToolbarTitle());
        setSupportActionBar(toolbar);

        initDrawer();

        topLevelPresenter.initialize();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_top_level;
    }

    public void openNavigationDrawer() {
        navigationDrawerLayout.openDrawer(GravityCompat.START);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @StringRes
    public abstract int getToolbarTitle();

    protected void handleUpArrowSelected() {
        openNavigationDrawer();
    }

    @IdRes
    protected abstract int getNavigationMenuItemId();

    private void initDrawer() {
        navigationView.setNavigationItemSelectedListener(item -> {
            processNavigationItem(item);
            navigationDrawerLayout.closeDrawer(GravityCompat.START);
            return false;
        });
    }

    private void processNavigationItem(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_nav_volumes:
                topLevelPresenter.onVolumesSelected();
                break;
            case R.id.menu_nav_schedule:
                topLevelPresenter.onScheduleSelected();
                break;
//            case R.id.menu_nav_profiles:
//                topLevelPresenter.onProfileSelected();
//                break;
            case R.id.menu_nav_settings:
                topLevelPresenter.onSettingSelected();
                break;
            case R.id.menu_nav_about:
                topLevelPresenter.onAboutSelected();
                break;
            default:
                throw new IllegalArgumentException("Invalid navigation item: " + item.getTitle());
        }
    }
}
