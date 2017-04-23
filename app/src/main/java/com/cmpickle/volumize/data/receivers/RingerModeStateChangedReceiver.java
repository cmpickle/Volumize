package com.cmpickle.volumize.data.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cmpickle.volumize.util.preferences.BasePreferences;
import com.cmpickle.volumize.util.preferences.Preferences;
import com.cmpickle.volumize.view.dialogs.VolumeRestoreDialog;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/20/2017.
 */

public class RingerModeStateChangedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int ringerMode = intent.getIntExtra(AudioManager.EXTRA_RINGER_MODE, -1);
        Log.d("Ringer Mode Receiver", ""+ringerMode);
        Preferences preferences = new Preferences(PreferenceManager.getDefaultSharedPreferences(context)); //todo: isn't finding shared preferences with context...
        if((ringerMode==AudioManager.RINGER_MODE_SILENT || ringerMode==AudioManager.RINGER_MODE_VIBRATE) ) { //todo: add preferences.displayVolumeRestoreDialog as criteria
            Intent mIntent = new Intent(context, VolumeRestoreDialog.class);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mIntent);
        }
    }
}
