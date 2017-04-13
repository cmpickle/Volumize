package com.cmpickle.volumize.view.volumeadjust;

import com.cmpickle.volumize.view.BasePresenter;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

public class VolumeAdjustPresenter extends BasePresenter {

    VolumeAdjustView volumeAdjustView;

    @Inject
    public VolumeAdjustPresenter() {

    }

    @Override
    protected void setView(Object o) {
        this.volumeAdjustView = (VolumeAdjustView) o;
    }

    public void seekBarRingToneMoved(int progress) {
        volumeAdjustView.setRingToneEditText(progress);
    }

    public void seekBarMediaVolumeMoved(int progress) {
        volumeAdjustView.setMediaVolumeEditText(progress);
    }

    public void seekBarNotificationMoved(int progress) {
        volumeAdjustView.setNotificationsEditText(progress);
    }

    public void seekBarSystemVolumeMoved(int progress) {
        volumeAdjustView.setSystemVolumeEditText(progress);
    }
}
