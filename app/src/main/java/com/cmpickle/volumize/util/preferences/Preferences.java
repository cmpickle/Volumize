package com.cmpickle.volumize.util.preferences;

import android.content.SharedPreferences;
import android.media.AudioManager;

import com.raizlabs.android.dbflow.sql.language.Condition;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/15/2017.
 */

public class Preferences extends BasePreferences<Preferences> {

    public static final String PREF_PAUSE_EVENTS = "pref_pause_events";
    public static final String PREF_DISPLAY_VOLUME_RESTORE_DIALOG = "pref_display_volume_restore_dialog";
    public static final String PREF_PAUSE_VOLUME_RESTORE_DIALOG = "pref_pause_volume_restore_dialog";
    public static final String PREF_SORT_ORDER = "pref_sort_order";
    public static final String PREF_SORT_TYPE = "pref_sort_type";
    public static final String PREF_PREVIOUS_RINGER_TYPE = "pref_previous_ringer_type";

    public static final String PREF_RATE_APP = "pref_rate_app";
    public static final String PREF_HOMEPAGE = "pref_homepage";
    public static final String PREF_FEATURE_REQUESTS = "pref_feature_requests";
    public static final String PREF_FEEDBACK = "pref_feedback";
    public static final String PREF_RECOGNITIONS = "pref_recognitions";

    @Inject
    public Preferences(SharedPreferences sharedPreferences) {
        super(sharedPreferences);
    }

    public void init() {
        if(getDisplayVolumeRestoreDialog() == null) {
            setDisplayVolumeRestoreDialog(true);
        }
        if(getPrefPauseVolumeRestoreDialog() == null) {
            setPrefPauseVolumeRestoreDialog(false);
        }
        if(getPauseScheduledEvents() == null) {
            setPauseScheduledEvents(false);
        }
        if(getSortOrder() == null) {
            setSortOrder("ascending");
        }
        if(getSortType() == null) {
            setSortType("todaysfirst");
        }
        if(getPrefPreviousRingerType() == null) {
            setPrefPreviousRingerType(AudioManager.RINGER_MODE_NORMAL);
        }
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

    public Boolean getPrefPauseVolumeRestoreDialog() {
        return getBoolean(PREF_PAUSE_VOLUME_RESTORE_DIALOG);
    }

    public void setPrefPauseVolumeRestoreDialog(Boolean pause) {
        setBoolean(PREF_PAUSE_VOLUME_RESTORE_DIALOG, pause);
    }

    public Integer getPrefPreviousRingerType() {
        return getInteger(PREF_PREVIOUS_RINGER_TYPE);
    }

    public void setPrefPreviousRingerType(int type) {
        setIngeger(PREF_PREVIOUS_RINGER_TYPE, type);
    }

    public Boolean getPauseScheduledEvents() {
        return getBoolean(PREF_PAUSE_EVENTS);
    }

    public void setPauseScheduledEvents(Boolean pause) {
        setBoolean(PREF_PAUSE_EVENTS, pause);
    }

    public String getSortOrder() {
        return getString(PREF_SORT_ORDER);
    }

    public void setSortOrder(String sortOrder) {
        setString(PREF_SORT_ORDER, sortOrder);
    }

    public String getSortType() {
        return getString(PREF_SORT_TYPE);
    }

    public void setSortType(String sortType) {
        setString(PREF_SORT_TYPE, sortType);
    }
}
