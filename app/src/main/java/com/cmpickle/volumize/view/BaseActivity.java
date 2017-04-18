package com.cmpickle.volumize.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.alerts.AlertDialogParams;
import com.cmpickle.volumize.view.alerts.AlertListener;
import com.cmpickle.volumize.view.alerts.AlertUtil;
import com.cmpickle.volumize.view.alerts.Alerts;
import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/1/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements AlertListener {

    public static final int FRAME_CONTAINER_ID = R.id.frame_container;

    @Inject
    Alerts alerts;

    private AlertUtil alertUtil;

    @Override
    public void onAlertLeftButton(AlertDialogParams params) {
        //do nothing by default
    }

    @Override
    public void onAlertRightButton(AlertDialogParams params) {
        //do nothing by default
    }

    @Override
    public void onAlertDismissed(AlertDialogParams params) {
        //do nothing by default
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);

        Stetho.initializeWithDefaults(this);

        setContentView(getLayoutResId());

        addFragment();

        alertUtil = new AlertUtil(alerts, this, this);
        alertUtil.restore(savedInstanceState);
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        alertUtil.destroy();

        super.onDestroy();
    }

    public void showError(@StringRes int errorTextResourceId) {
        alertUtil.showError(errorTextResourceId);
    }

    public void showAlert(AlertDialogParams params) {
        alertUtil.showAlert(params);
    }

    protected abstract Fragment createFragment();

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_base;
    }

    protected void addFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.frame_container);

        if(fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.frame_container, fragment)
                    .commit();
        }
    }

    protected void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    protected Fragment getFragment() {
        return getSupportFragmentManager().findFragmentById(FRAME_CONTAINER_ID);
    }
}
