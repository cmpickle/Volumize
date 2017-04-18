package com.cmpickle.volumize.view.settings;

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

public class SettingsFragment extends PreferenceFragmentCompat implements SettingsView {

    @Inject
    SettingsPresenter settingsPresenter;

    public SettingsFragment() {
        Injector.get().inject(this);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settingsPresenter.setView(this);
        settingsPresenter.initialize();
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        switch (preference.getKey()) {
            case Preferences.PREF_PAUSE_EVENTS:
                return true;
            case Preferences.PREF_DISPLAY_VOLUME_RESTORE_DIALOG:
                return true;
            default:
                return super.onPreferenceTreeClick(preference);
        }
    }

    @Override
    public void buildFragment() {
       addPreferencesFromResource(R.xml.settings);
    }

    public static SettingsFragment newInstance() {return new SettingsFragment(); }
}
