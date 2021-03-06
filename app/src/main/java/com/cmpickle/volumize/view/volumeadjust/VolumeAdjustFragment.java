package com.cmpickle.volumize.view.volumeadjust;

import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.R;
import com.cmpickle.volumize.data.observer.AudioContentObserver;
import com.cmpickle.volumize.view.BaseFragment;
import com.cmpickle.volumize.view.BasePresenter;
import com.cmpickle.volumize.view.adapter.OnSeekBarChangedAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

public class VolumeAdjustFragment extends BaseFragment implements VolumeAdjustView {

    @Inject
    VolumeAdjustPresenter volumeAdjustPresenter;
    AudioContentObserver observer;

    @BindView(R.id.tv_ring_tone_amount)
    TextView tvRingToneAmount;
    @BindView(R.id.tv_media_volume_amount)
    TextView tvMediaVolumeAmount;
    @BindView(R.id.tv_notifications_amount)
    TextView tvNotificationsAmount;
    @BindView(R.id.tv_system_volume_amount)
    TextView tvSystemVolumeAmount;

    @BindView(R.id.seek_bar_ring_tone)
    SeekBar seekBarRingTone;
    @BindView(R.id.seek_bar_media_volume)
    SeekBar seekBarMediaVolume;
    @BindView(R.id.seek_bar_notifications)
    SeekBar seekBarNotifications;
    @BindView(R.id.seek_bar_system_volume)
    SeekBar seekBarSystemVolume;

    @BindView(R.id.switch_vibrate_adjust)
    SwitchCompat switchVibrate;

    @BindView(R.id.adView)
    AdView adView;

    public VolumeAdjustFragment() {
        Injector.get().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_volume_adjust, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        volumeAdjustPresenter.onViewCreated();

        seekBarRingTone.setOnSeekBarChangeListener(new OnSeekBarChangedAdapter() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volumeAdjustPresenter.seekBarRingToneMoved(seekBar.getProgress());
            }
        });

        seekBarMediaVolume.setOnSeekBarChangeListener(new OnSeekBarChangedAdapter() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volumeAdjustPresenter.seekBarMediaVolumeMoved(seekBar.getProgress());
            }
        });

        seekBarNotifications.setOnSeekBarChangeListener(new OnSeekBarChangedAdapter() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volumeAdjustPresenter.seekBarNotificationMoved(seekBar.getProgress());
            }
        });

        seekBarSystemVolume.setOnSeekBarChangeListener(new OnSeekBarChangedAdapter() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volumeAdjustPresenter.seekBarSystemVolumeMoved(seekBar.getProgress());
            }
        });

        observer = new AudioContentObserver(getActivity(), new Handler());
        getActivity().getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, observer);
        observer.setVolumeAdjustController(this);

        setupAd();
    }

    @Override
    protected void onSetViewAndRouterOnPresenter() {
        volumeAdjustPresenter.setView(this);
        volumeAdjustPresenter.setRouter((VolumeAdjustRouter) getActivity());
    }

    @Override
    protected BasePresenter getPresenter() {
        return volumeAdjustPresenter;
    }

    @Override
    public void setRingToneTextView(int progress) {
        tvRingToneAmount.setText(String.format(getContext().getString(R.string.volume_placeholder), progress));
    }

    @Override
    public void setMediaVolumeTextView(int progress) {
        tvMediaVolumeAmount.setText(String.format(getContext().getString(R.string.volume_placeholder), progress));
    }

    @Override
    public void setNotificationsTextView(int progress) {
        tvNotificationsAmount.setText(String.format(getContext().getString(R.string.volume_placeholder), progress));
    }

    @Override
    public void setSystemVolumeTextView(int progress) {
        tvSystemVolumeAmount.setText(String.format(getContext().getString(R.string.volume_placeholder), progress));
    }

    @Override
    public void setRingToneSeekBarMaxValue(int max) {
        seekBarRingTone.setMax(max);
    }

    @Override
    public void setMediaVolumeSeekBarMaxValue(int max) {
        seekBarMediaVolume.setMax(max);
    }

    @Override
    public void setNotificationsSeekBarMaxValue(int max) {
        seekBarNotifications.setMax(max);
    }

    @Override
    public void setSystemVolumeSeekBarMaxValue(int max) {
        seekBarSystemVolume.setMax(max);
    }

    @Override
    public void setRingToneSeekBarCurrentValue(int currentValue) {
        if(seekBarRingTone != null)
            seekBarRingTone.setProgress(currentValue);
    }

    @Override
    public void setMediaVolumeSeekBarCurrentValue(int currentValue) {
        if(seekBarMediaVolume != null)
            seekBarMediaVolume.setProgress(currentValue);
    }

    @Override
    public void setNotificationsSeekBarCurrentValue(int currentValue) {
        if(seekBarNotifications != null)
            seekBarNotifications.setProgress(currentValue);
    }

    @Override
    public void setSystemVolumeSeekBarCurrentValue(int currentValue) {
        if(seekBarSystemVolume != null)
            seekBarSystemVolume.setProgress(currentValue);
    }

    @Override
    public void setVibrateSwitchCurrentValue(boolean currentValue) {
        if(switchVibrate != null)
            switchVibrate.setChecked(currentValue);
    }

    @Override
    public void setRingerMuteView() {
        tvNotificationsAmount.setEnabled(false);
        seekBarNotifications.setEnabled(false);
        tvSystemVolumeAmount.setEnabled(false);
        seekBarSystemVolume.setEnabled(false);
        switchVibrate.setEnabled(true);
    }

    @Override
    public void setRingerUnmutedView() {
        tvNotificationsAmount.setEnabled(true);
        seekBarNotifications.setEnabled(true);
        tvSystemVolumeAmount.setEnabled(true);
        seekBarSystemVolume.setEnabled(true);
        switchVibrate.setChecked(true);
        switchVibrate.setEnabled(false);
    }

    @Override
    public boolean isMutedView() {
        return !tvNotificationsAmount.isEnabled() && !tvSystemVolumeAmount.isEnabled();
    }

    @OnClick(R.id.switch_vibrate_adjust)
    public void onVibrateSwitch() {
        volumeAdjustPresenter.onVibrateSwitch(switchVibrate.isChecked());
    }

    private void setupAd() {
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        adRequestBuilder.addTestDevice("D7125D1B856F1556CBD9932A7F86FC5C");
        adView.loadAd(adRequestBuilder.build());
    }
}
