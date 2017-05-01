package com.cmpickle.volumize.data.observer;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;

import com.cmpickle.volumize.view.volumeadjust.VolumeAdjustView;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/15/2017.
 */

public class AudioContentObserver extends ContentObserver {

    private VolumeAdjustView volumeAdjustView;
    private int previousRingVolume;
    private int previousMediaVolume;
    private int previousNotificationVolume;
    private int previousSystemVolume;
    private final AudioManager audio;

    public AudioContentObserver(Context c, Handler handler) {
        super(handler);

        audio = (AudioManager) c.getSystemService(Context.AUDIO_SERVICE);
        previousRingVolume = audio.getStreamVolume(AudioManager.STREAM_RING);
        previousMediaVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        previousNotificationVolume = audio.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
        previousSystemVolume = audio.getStreamVolume(AudioManager.STREAM_SYSTEM);
    }

    @Override
    public boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);

        int currentRingVolume = audio.getStreamVolume(AudioManager.STREAM_RING);
        int currentMediaVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        int currentNotificationVolume = audio.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
        int currentSystemVolume = audio.getStreamVolume(AudioManager.STREAM_SYSTEM);

//        int delta=previousRingVolume-currentVolume;
        previousRingVolume=currentRingVolume;
        volumeAdjustView.setRingToneSeekBarCurrentValue(currentRingVolume);
        previousMediaVolume=currentMediaVolume;
        volumeAdjustView.setMediaVolumeSeekBarCurrentValue(currentMediaVolume);
        previousNotificationVolume=currentNotificationVolume;
        if(currentNotificationVolume != 0)
            volumeAdjustView.setNotificationsSeekBarCurrentValue(currentNotificationVolume);
        previousSystemVolume=currentSystemVolume;
        if(currentSystemVolume != 0)
            volumeAdjustView.setSystemVolumeSeekBarCurrentValue(currentSystemVolume);
    }

    public void setVolumeAdjustController(VolumeAdjustView volumeAdjustView) {
        this.volumeAdjustView = volumeAdjustView;
    }
}
