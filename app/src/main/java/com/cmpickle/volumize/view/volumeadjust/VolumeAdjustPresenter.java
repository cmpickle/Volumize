package com.cmpickle.volumize.view.volumeadjust;

import com.cmpickle.volumize.domain.VolumeService;
import com.cmpickle.volumize.view.BasePresenter;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

public class VolumeAdjustPresenter extends BasePresenter<VolumeAdjustView> {

    VolumeService volumeService;
    VolumeAdjustView volumeAdjustView;

    @Inject
    public VolumeAdjustPresenter(VolumeService volumeService) {
        this.volumeService = volumeService;
    }

    @Override
    protected void setView(VolumeAdjustView volumeAdjustView) {
        this.volumeAdjustView = volumeAdjustView;
    }

    public void onViewCreated() {
        volumeAdjustView.setRingToneEditTextMaxValue(volumeService.getRingToneMaxVolume());
        volumeAdjustView.setMediaVolumeEditTextMaxValue(volumeService.getMediaVolumeMaxVolume());
        volumeAdjustView.setNotificationsEditTextMaxValue(volumeService.getNotificationsMaxVolume());
        volumeAdjustView.setSystemVolumeEditTextMaxValue(volumeService.getSystemVolumeMaxVolume());

        volumeAdjustView.setRingToneEditText(volumeService.getRingToneVolume());
        volumeAdjustView.setMediaVolumeEditText(volumeService.getMediaVolumeVolume());
        volumeAdjustView.setNotificationsEditText(volumeService.getNotificationsVolume());
        volumeAdjustView.setSystemVolumeEditText(volumeService.getSystemVolumeVolume());

        volumeAdjustView.setRingToneEditTextCurrentValue(volumeService.getRingToneVolume());
        volumeAdjustView.setMediaVolumeEditTextCurrentValue(volumeService.getMediaVolumeVolume());
        volumeAdjustView.setNotificationsEditTextCurrentValue(volumeService.getNotificationsVolume());
        volumeAdjustView.setSystemVolumeEditTextCurrentValue(volumeService.getSystemVolumeVolume());
    }

    public void seekBarRingToneMoved(int progress) {
        volumeService.setRingToneVolume(progress);
        volumeAdjustView.setRingToneEditText(progress);
    }

    public void seekBarMediaVolumeMoved(int progress) {
        volumeService.setMediaVolume(progress);
        volumeAdjustView.setMediaVolumeEditText(progress);
    }

    public void seekBarNotificationMoved(int progress) {
        volumeService.setNotificationVolume(progress);
        volumeAdjustView.setNotificationsEditText(progress);
    }

    public void seekBarSystemVolumeMoved(int progress) {
        volumeService.setSystemVolume(progress);
        volumeAdjustView.setSystemVolumeEditText(progress);
    }
}
