package com.cmpickle.volumize.domain;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/13/2017.
 */

public class VolumeService extends IntentFilter {

    AudioManager audioManager;
    public static final String OPTION = "option";
    public static final String AMOUNT = "amount";
    public static final String VIBRATE = "vibrate";
    public static final String REPEAT_WEEKLY = "repeatWeekly";
    public static final String DAYS = "days";
    public static final String ACTIVE = "active";

    public static final int ALL = 0;
    public static final int RING_TONE = 1;
    public static final int MEDIA = 2;
    public static final int NOTIFICATIONS = 3;
    public static final int SYSTEM = 4;


    @Inject
    public VolumeService(Context context) {
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public int getRingToneMaxVolume() {
        return audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
    }

    public int getMediaVolumeMaxVolume() {
        return audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }

    public int getNotificationsMaxVolume() {
        return audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);
    }

    public int getSystemVolumeMaxVolume() {
        return audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
    }

    public int getRingToneVolume() {
        return audioManager.getStreamVolume(AudioManager.STREAM_RING);
    }

    public int getMediaVolumeVolume() {
        return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    public int getNotificationsVolume() {
        return audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
    }

    public int getSystemVolume() {
        return audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
    }

    public void setRingToneVolume(int level) {
        audioManager.setStreamVolume(AudioManager.STREAM_RING, level, AudioManager.FLAG_PLAY_SOUND);
    }

    public void setMediaVolume(int level) {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, level, AudioManager.FLAG_PLAY_SOUND);
    }

    public void setNotificationVolume(int level) {
        audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, level, AudioManager.FLAG_PLAY_SOUND);
    }

    public void setSystemVolume(int level) {
        audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, level, AudioManager.FLAG_PLAY_SOUND);
    }
}
