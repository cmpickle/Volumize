package com.cmpickle.volumize.view.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.R;
import com.cmpickle.volumize.util.preferences.Preferences;

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        aboutPresenter.setView(this);
        aboutPresenter.setRouter((AboutRouter) getActivity());
        aboutPresenter.initialize();
    }

    @Override
    public void buildFragment() {
        addPreferencesFromResource(R.xml.about);
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        switch (preference.getKey()) {
            case Preferences.PREF_RECOGNITIONS:
                aboutPresenter.moveToRecognitionsPage();
                return true;
            default:
                return super.onPreferenceTreeClick(preference);
        }
    }

    @Override
    public void setVersionNumber() {
//        tvVolumizeVersion.setText(BuildConfig.VERSION_NAME);
    }

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }
}
