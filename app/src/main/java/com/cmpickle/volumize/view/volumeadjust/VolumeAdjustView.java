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

    void setRingToneEditTextMaxValue(int max);

    void setMediaVolumeEditTextMaxValue(int max);

    void setNotificationsEditTextMaxValue(int max);

    void setSystemVolumeEditTextMaxValue(int max);

    void setRingToneEditTextCurrentValue(int currentValue);

    void setMediaVolumeEditTextCurrentValue(int currentValue);

    void setNotificationsEditTextCurrentValue(int currentValue);

    void setSystemVolumeEditTextCurrentValue(int currentValue);
}
