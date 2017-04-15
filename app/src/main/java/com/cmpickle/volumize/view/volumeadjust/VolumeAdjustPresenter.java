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
        volumeAdjustView.setRingToneSeekBarMaxValue(volumeService.getRingToneMaxVolume());
        volumeAdjustView.setMediaVolumeSeekBarMaxValue(volumeService.getMediaVolumeMaxVolume());
        volumeAdjustView.setNotificationsSeekBarMaxValue(volumeService.getNotificationsMaxVolume());
        volumeAdjustView.setSystemVolumeSeekBarMaxValue(volumeService.getSystemVolumeMaxVolume());

        volumeAdjustView.setRingToneTextView(volumeService.getRingToneVolume());
        volumeAdjustView.setMediaVolumeTextView(volumeService.getMediaVolumeVolume());
        volumeAdjustView.setNotificationsTextView(volumeService.getNotificationsVolume());
        volumeAdjustView.setSystemVolumeTextView(volumeService.getSystemVolume());

        volumeAdjustView.setRingToneSeekBarCurrentValue(volumeService.getRingToneVolume());
        volumeAdjustView.setMediaVolumeSeekBarCurrentValue(volumeService.getMediaVolumeVolume());
        volumeAdjustView.setNotificationsSeekBarCurrentValue(volumeService.getNotificationsVolume());
        volumeAdjustView.setSystemVolumeSeekBarCurrentValue(volumeService.getSystemVolume());
    }

    public void seekBarRingToneMoved(int progress) {
        if(progress == 0) {
            volumeAdjustView.setRingerMuteView();
        } else if(volumeAdjustView.isMutedView()) {
            volumeAdjustView.setRingerUnmutedView();
        }
        volumeService.setRingToneVolume(progress);
        volumeAdjustView.setRingToneTextView(progress);
    }

    public void seekBarMediaVolumeMoved(int progress) {
        volumeService.setMediaVolume(progress);
        volumeAdjustView.setMediaVolumeTextView(progress);
    }

    public void seekBarNotificationMoved(int progress) {
        volumeService.setNotificationVolume(progress);
        volumeAdjustView.setNotificationsTextView(progress);
    }

    public void seekBarSystemVolumeMoved(int progress) {
        volumeService.setSystemVolume(progress);
        volumeAdjustView.setSystemVolumeTextView(progress);
    }
}
