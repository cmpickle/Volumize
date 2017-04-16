package com.cmpickle.volumize.view.schedule.edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.BasePresenter;
import com.cmpickle.volumize.view.alerts.AlertDialogParams;
import com.cmpickle.volumize.view.alerts.AlertType;
import com.cmpickle.volumize.view.edit.EditFragment;

import javax.inject.Inject;

import butterknife.BindView;
import icepick.State;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/9/2017.
 */

public class EditScheduleFragment extends EditFragment implements EditScheduleView {

    @Inject
    EditSchedulePresenter editSchedulePresenter;

    @BindView(R.id.edit_schedule_time_picker)
    EditText timePicker;

    @BindView(R.id.switch_reoccurring)
    SwitchCompat switchReoccurring;

    @BindView(R.id.days_of_week_top)
    LinearLayout daysOfWeekTop;
    @BindView(R.id.days_of_week_bottom)
    LinearLayout daysOfWeekBottom;

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

    @BindView(R.id.seek_bar_edit_schedule_volume)
    SeekBar seekBarVolume;

    @BindView(R.id.switch_vibrate)
    SwitchCompat switchVibrate;

    @State
    boolean reoccurringViewInvisible = false;

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

        timePicker.setOnClickListener(v -> openTimePicker());
        switchReoccurring.setOnClickListener(v -> editSchedulePresenter.onReoccurringSwitched());
        switchReoccurring.setChecked(reoccurringViewInvisible);
        editSchedulePresenter.onReoccurringSwitched();
        switchMute.setOnClickListener(v -> editSchedulePresenter.onMuteSwitched());
        editSchedulePresenter.onViewCreated();
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

    @StringRes
    public int getTimePickerTitleId() {
        return R.string.time_picker;
    }

    public void openTimePicker() {
        AlertDialogParams params = new AlertDialogParams(null, getTimePickerTitleId());
        params.setRightButtonTextResourceId(android.R.string.ok);
        params.setLeftButtonTextResourceId(R.string.common_cancel);
        params.setType(AlertType.TIME_PICKER);
        showAlert(params);
    }

    @Override
    public void updateReoccurringView() {
        if(switchReoccurring.isChecked()) {
            daysOfWeekTop.setVisibility(View.VISIBLE);
            daysOfWeekTop.invalidate();
            daysOfWeekBottom.setVisibility(View.VISIBLE);
            daysOfWeekBottom.invalidate();
            reoccurringViewInvisible = false;
        } else {
            daysOfWeekTop.setVisibility(View.GONE);
            daysOfWeekBottom.setVisibility(View.GONE);
            reoccurringViewInvisible = true;
        }
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
