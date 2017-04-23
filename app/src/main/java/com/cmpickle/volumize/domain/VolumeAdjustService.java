package com.cmpickle.volumize.domain;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/22/2017.
 */

public class VolumeAdjustService extends IntentService {

    @Inject
    VolumeService volumeService;

    public VolumeAdjustService() {
        super("VolumeAdjustService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("VolumeAdjustService", "volume adjusted");
        volumeService.setRingToneVolume(15);
    }
}
