package com.cmpickle.volumize.view.dialogs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.TimePicker;

import com.cmpickle.volumize.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/20/2017.
 */

public class VolumeRestoreDialog extends Activity {

    @BindView(R.id.time_picker_restore)
    TimePicker timePickerRestore;
    @BindView(R.id.btn_do_not_restore)
    Button btnDoNotRestore;
    @BindView(R.id.btn_restore)
    Button btnRestore;

    public VolumeRestoreDialog() {
        //required empty constructor
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_dialog);
        ButterKnife.bind(this);

        timePickerRestore.setIs24HourView(true);
        btnDoNotRestore.setOnClickListener(v -> VolumeRestoreDialog.this.finish());
        btnRestore.setOnClickListener(v -> Log.d("VolumeRestoreDialog", "Store restore time"));
    }
}
