package com.cmpickle.volumize.view.dialogs;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TimePicker;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.adapter.OnSeekBarChangedAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/20/2017.
 */

public class VolumeRestoreDialog extends Activity implements VolumeRestoreView {

    @BindView(R.id.time_picker_restore)
    TimePicker timePickerRestore;
    @BindView(R.id.btn_do_not_restore)
    Button btnDoNotRestore;
    @BindView(R.id.btn_restore)
    Button btnRestore;
    @BindView(R.id.seek_bar_volume_restore)
    SeekBar volumeRestore;

    @Inject
    VolumeRestorePresenter presenter;

    public VolumeRestoreDialog() {
        Injector.get().inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_dialog);
        ButterKnife.bind(this);

        presenter.setView(this);
        presenter.onCreate();

        volumeRestore.setOnSeekBarChangeListener(new OnSeekBarChangedAdapter() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress < 1) {
                    volumeRestore.setProgress(1);
                }
            }
        });

        timePickerRestore.setIs24HourView(true);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePickerRestore.setHour(1);
            timePickerRestore.setMinute(0);
        } else {
            timePickerRestore.setCurrentHour(1);
            timePickerRestore.setCurrentMinute(0);
        }
        btnDoNotRestore.setOnClickListener(v -> VolumeRestoreDialog.this.finish());
        btnRestore.setOnClickListener(v -> {
            presenter.onRestoreClicked(timePickerRestore.getCurrentHour(), timePickerRestore.getCurrentMinute(), volumeRestore.getProgress());
            VolumeRestoreDialog.this.finish();
        });
    }

    @Override
    public void setMaxVolumeRestoreSeekBar(int max) {
        volumeRestore.setMax(max);
    }
}
