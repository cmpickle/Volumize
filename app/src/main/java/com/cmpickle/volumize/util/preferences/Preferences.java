package com.cmpickle.volumize.util.preferences;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/15/2017.
 */

public class Preferences extends BasePreferences<Preferences> {

    public static final String PREF_PAUSE_EVENTS = "pref_pause_events";
    public static final String PREF_DISPLAY_VOLUME_RESTORE_DIALOG = "pref_display_volume_restore_dialog";

    public static final String PREF_RECOGNITIONS = "pref_recognitions";

    @Inject
    public Preferences(SharedPreferences sharedPreferences) {
        super(sharedPreferences);
    }

//    public String getPrefPauseEvents() {
//        return getSecret(PREF_PAUSE_EVENTS);
//    }

//    public Preferences withPrefPauseEvents(String prefPauseEvents) {
//        return withSecret(PREF_PAUSE_EVENTS, prefPauseEvents);
//    }

    public Boolean getDisplayVolumeRestoreDialog() {
        return getBoolean(PREF_DISPLAY_VOLUME_RESTORE_DIALOG);
    }

    public void setDisplayVolumeRestoreDialog(Boolean display) {
        setBoolean(PREF_DISPLAY_VOLUME_RESTORE_DIALOG, display);
    }
}
