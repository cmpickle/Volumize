package com.cmpickle.volumize.data.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.cmpickle.volumize.domain.VolumeService;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/22/2017.
 */

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

    VolumeService volumeService;

    @Override
    public void onReceive(Context context, Intent intent) {
        volumeService = new VolumeService(context);
        int amount = intent.getIntExtra(VolumeService.AMOUNT, 12);
        Log.d("AlarmReceiver", "The volume is now " + amount);
//        Toast.makeText(context, "This is where the volume will be changed to " + amount, Toast.LENGTH_LONG).show();
        switch (intent.getIntExtra(VolumeService.OPTION, 0)) {
            case VolumeService.ALL:
                volumeService.setRingToneVolume(amount);
                volumeService.setNotificationVolume(amount);
                volumeService.setMediaVolume(amount);
                volumeService.setSystemVolume(amount);
                break;
            case VolumeService.RING_TONE:
                volumeService.setRingToneVolume(amount);
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
//        Intent mIntent = new Intent(context, VolumeAdjustService.class);
//        context.startService(mIntent);
    }
}
