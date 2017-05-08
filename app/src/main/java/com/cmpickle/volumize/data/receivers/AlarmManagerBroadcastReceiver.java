package com.cmpickle.volumize.data.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.data.repositories.ScheduleEventRepository;
import com.cmpickle.volumize.domain.RestoreHelper;
import com.cmpickle.volumize.domain.ScheduleEventService;
import com.cmpickle.volumize.domain.VolumeService;
import com.cmpickle.volumize.util.preferences.Preferences;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/22/2017.
 */

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

    public static final String ACTION_CHANGE_VOLUME = "change_volume";
    public static final String ACTION_CHANGE_VOLUME_TEMP = "change_volume_temp";

    VolumeService volumeService;

    @Inject
    RestoreHelper helper;

    public AlarmManagerBroadcastReceiver() {
        Injector.get().inject(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Preferences preferences = new Preferences(PreferenceManager.getDefaultSharedPreferences(context));
        if(intent.getBooleanExtra(VolumeService.ACTIVE, true) && !preferences.getPauseScheduledEvents()) {
            volumeService = new VolumeService(context);
            int amount = intent.getIntExtra(VolumeService.AMOUNT, 12);
            Log.d("AlarmReceiver", "The volume is now " + amount);
            switch (intent.getIntExtra(VolumeService.OPTION, 0)) {
                case VolumeService.ALL:
                    volumeService.setRingToneVolume(amount, intent.getBooleanExtra(VolumeService.VIBRATE, true));
                    volumeService.setNotificationVolume(amount);
                    volumeService.setMediaVolume(amount);
                    volumeService.setSystemVolume(amount);
                    break;
                case VolumeService.RING_TONE:
                    volumeService.setRingToneVolume(amount, intent.getBooleanExtra(VolumeService.VIBRATE, true));
                    break;
                case VolumeService.MEDIA:
                    volumeService.setMediaVolume(amount);
                    break;
                case VolumeService.NOTIFICATIONS:
                    volumeService.setNotificationVolume(amount);
                    break;
                case VolumeService.SYSTEM:
                    volumeService.setSystemVolume(amount);
                    break;
            }
        }
        if(intent.getAction().equals(ACTION_CHANGE_VOLUME_TEMP)) {
            helper.getEventService().deleteEvent(intent.getStringExtra("eventId"));
        }
    }
}
