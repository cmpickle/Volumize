package com.cmpickle.volumize.view.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.BuildConfig;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.R;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/7/2017.
 */

public class AboutFragment extends PreferenceFragmentCompat implements AboutView {

    @Inject
    AboutPresenter aboutPresenter;

    public AboutFragment() {
        Injector.get().inject(this);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final AboutActivity activity = (AboutActivity) getActivity();
        Toolbar toolbar = activity.getToolbar();
        toolbar.setNavigationOnClickListener(v -> activity.openNavigationDrawer());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        aboutPresenter.setView(this);
        aboutPresenter.initialize();
    }

    @Override
    public void buildFragment() {
        addPreferencesFromResource(R.xml.about);
    }

    @Override
    public void setVersionNumber() {
//        tvVolumizeVersion.setText(BuildConfig.VERSION_NAME);
    }

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }
}
