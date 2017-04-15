package com.cmpickle.volumize.view.volumeadjust;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.BaseFragment;
import com.cmpickle.volumize.view.BasePresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

public class VolumeAdjustFragment extends BaseFragment implements VolumeAdjustView {

    @Inject
    VolumeAdjustPresenter volumeAdjustPresenter;

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

        final VolumeAdjustActivity activity = (VolumeAdjustActivity) getActivity();
        Toolbar toolbar = activity.getToolbar();
        toolbar.setNavigationOnClickListener(v -> activity.openNavigationDrawer());

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
    }

    @Override
    protected void onSetViewAndRouterOnPresenter() {
        volumeAdjustPresenter.setView(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return volumeAdjustPresenter;
    }

    @Override
    public void setRingToneEditText(int progress) {
        tvRingToneAmount.setText(String.format(getContext().getString(R.string.volume_placeholder), progress));
    }

    @Override
    public void setMediaVolumeEditText(int progress) {
        tvMediaVolumeAmount.setText(String.format(getContext().getString(R.string.volume_placeholder), progress));
    }

    @Override
    public void setNotificationsEditText(int progress) {
        tvNotificationsAmount.setText(String.format(getContext().getString(R.string.volume_placeholder), progress));
    }

    @Override
    public void setSystemVolumeEditText(int progress) {
        tvSystemVolumeAmount.setText(String.format(getContext().getString(R.string.volume_placeholder), progress));
    }

    private abstract class OnSeekBarChangedAdapter implements SeekBar.OnSeekBarChangeListener {
        @Override
        public abstract void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser);

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    }
}
