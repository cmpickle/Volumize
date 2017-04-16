package com.cmpickle.volumize.view.schedule.edit;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.BasePresenter;
import com.cmpickle.volumize.view.adapter.OnSeekBarChangedAdapter;
import com.cmpickle.volumize.view.edit.EditFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/9/2017.
 */

public class EditScheduleFragment extends EditFragment implements EditScheduleView {

    @Inject
    EditSchedulePresenter editSchedulePresenter;

    @BindView(R.id.edit_schedule_time_picker)
    EditText timePicker;

    @BindView(R.id.switch_repeat_weekly)
    SwitchCompat switchRepeatWeekly;

    @BindView(R.id.toggle_monday)
    ToggleButton mondayToggle;
    @BindView(R.id.toggle_tuesday)
    ToggleButton tuesdayToggle;
    @BindView(R.id.toggle_wednesday)
    ToggleButton wednesdayToggle;
    @BindView(R.id.toggle_thursday)
    ToggleButton thursdayToggle;
    @BindView(R.id.toggle_friday)
    ToggleButton fridayToggle;
    @BindView(R.id.toggle_saturday)
    ToggleButton saturdayToggle;

    @BindView(R.id.spinner_type)
    Spinner spinnerType;

    @BindView(R.id.switch_mute)
    SwitchCompat switchMute;

    @BindView(R.id.edit_schedule_volume_layout)
    RelativeLayout volumeLayout;
    @BindView(R.id.tv_volume_amount)
    TextView tvVolumeAmount;

    @BindView(R.id.seek_bar_edit_schedule_volume)
    SeekBar seekBarVolume;

    @BindView(R.id.switch_vibrate)
    SwitchCompat switchVibrate;

    public EditScheduleFragment() {
        Injector.get().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timePicker.setOnClickListener(v -> editSchedulePresenter.onTimePickerClicked());
        switchRepeatWeekly.setOnClickListener(v -> editSchedulePresenter.onRepeatWeeklySwitched());
        switchMute.setOnClickListener(v -> editSchedulePresenter.onMuteSwitched());
        seekBarVolume.setOnSeekBarChangeListener(new OnSeekBarChangedAdapter() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               editSchedulePresenter.onSeekBarMoved(seekBar.getProgress());
            }
        });
        editSchedulePresenter.onViewCreated();
    }

    @Override
    public void onResume() {
        super.onResume();
        editSchedulePresenter.onViewResumed();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    protected void onSetViewAndRouterOnPresenter() {
        editSchedulePresenter.setView(this);
        editSchedulePresenter.setScheduleRouter((EditScheduleRouter) getActivity());
    }

    @Override
    protected BasePresenter getPresenter() {
        return editSchedulePresenter;
    }

    @Override
    public void setVolumeTV(String level) {
       tvVolumeAmount.setText(level);
    }

    @Override
    public void openTimePicker() {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (view, hourOfDay, minute) -> {
            editSchedulePresenter.onTimePicked(hourOfDay, minute);
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), onTimeSetListener, 0, 0, DateFormat.is24HourFormat(getContext()));
        timePickerDialog.show();
    }

    @Override
    public void onTimePicked(String time) {
        timePicker.setText(time);
    }

    @Override
    public void updateMuteView() {
        if(switchMute.isChecked()) {
            volumeLayout.setVisibility(View.GONE);
        } else {
            volumeLayout.setVisibility(View.VISIBLE);
        }
    }
}
