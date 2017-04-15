package com.cmpickle.volumize.view.volumeadjust;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

public interface VolumeAdjustView {

    void setRingToneTextView(int progress);

    void setMediaVolumeTextView(int progress);

    void setNotificationsTextView(int progress);

    void setSystemVolumeTextView(int progress);

    void setRingToneSeekBarMaxValue(int max);

    void setMediaVolumeSeekBarMaxValue(int max);

    void setNotificationsSeekBarMaxValue(int max);

    void setSystemVolumeSeekBarMaxValue(int max);

    void setRingToneSeekBarCurrentValue(int currentValue);

    void setMediaVolumeSeekBarCurrentValue(int currentValue);

    void setNotificationsSeekBarCurrentValue(int currentValue);

    void setSystemVolumeSeekBarCurrentValue(int currentValue);

    void setMuteRingerView();

    void setRingerUnmutedView();
}
