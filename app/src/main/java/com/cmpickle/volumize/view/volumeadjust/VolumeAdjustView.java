package com.cmpickle.volumize.view.volumeadjust;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

public interface VolumeAdjustView {

    void setRingToneEditText(int progress);

    void setMediaVolumeEditText(int progress);

    void setNotificationsEditText(int progress);

    void setSystemVolumeEditText(int progress);
}
