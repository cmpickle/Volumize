package com.cmpickle.volumize.data.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cmpickle.volumize.domain.RestoreEventsService;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 5/7/2017.
 */

public class BootCompletedReceiver extends BroadcastReceiver {

    static final String ACTION = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ACTION)) {
            Intent serviceIntent = new Intent(context, RestoreEventsService.class);
            context.startService(serviceIntent);
        }
    }
}
