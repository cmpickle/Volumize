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
import com.cmpickle.volumize.data.dto.ScheduleEventInfo;
import com.cmpickle.volumize.view.BasePresenter;
import com.cmpickle.volumize.view.adapter.OnSeekBarChangedAdapter;
import com.cmpickle.volumize.view.edit.EditFragment;
import com.cmpickle.volumize.view.util.DayUtil;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemSelected;

import static com.cmpickle.volumize.view.util.DayUtil.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/9/2017.
 */

@FragmentWithArgs
public class EditScheduleFragment extends EditFragment implements EditScheduleView {

    @Inject
    EditSchedulePresenter editSchedulePresenter;

    @Arg
    String eventId;

    @BindView(R.id.edit_schedule_time_picker)
    EditText timePicker;

    @BindView(R.id.switch_repeat_weekly)
    SwitchCompat switchRepeatWeekly;

    @BindView(R.id.toggle_sunday)
    ToggleButton sundayToggle;
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
    @BindView(R.id.tv_edit_schedule_delete)
    TextView tvEditScheduleDelete;

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
//        switchRepeatWeekly.setOnClickListener(v -> editSchedulePresenter.onRepeatWeeklySwitched());
//        switchMute.setOnClickListener(v -> editSchedulePresenter.onMuteSwitched());
        seekBarVolume.setOnSeekBarChangeListener(new OnSeekBarChangedAdapter() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               editSchedulePresenter.onVolumeChanged(progress);
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
    public void openTimePicker(int hour, int minute) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (view, hourOfDay, minuteOfDay) -> editSchedulePresenter.onTimePicked(hourOfDay, minuteOfDay);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), onTimeSetListener, hour, minute, DateFormat.is24HourFormat(getContext()));
        timePickerDialog.show();
    }

    public static EditScheduleFragment newInstance(String eventId) {
        return new EditScheduleFragmentBuilder(eventId).build();
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

//    @Override
//    public int getOption() {
////        int pos = spinnerType.getSelectedItemPosition();
////        String[] optionValues = getResources().getStringArray(R.array.schedule_type_spinner_values);
////        return Integer.valueOf(optionValues[pos]);
//        return spinnerType.getSelectedItemPosition();
//    }
//
//    @Override
//    public int getAmount() {
//        if(switchMute.isChecked())
//            return 0;
//        return seekBarVolume.getProgress();
//    }
//
//    @Override
//    public boolean isVibrate() {
//        return switchVibrate.isChecked();
//    }
//
//    @Override
//    public boolean isRepeatWeekly() {
//        return switchRepeatWeekly.isChecked();
//    }
//
//    @Override
//    public int getDays() {
//        int days = 0;
//        if(sundayToggle.isChecked())
//            days += 1;
//        if(mondayToggle.isChecked())
//            days += 2;
//        if(tuesdayToggle.isChecked())
//            days += 4;
//        if(wednesdayToggle.isChecked())
//            days += 8;
//        if(thursdayToggle.isChecked())
//            days += 16;
//        if(fridayToggle.isChecked())
//            days += 32;
//        if(saturdayToggle.isChecked())
//            days += 64;
//        return days;
//    }

    @Override
    public String getEventId() {
        return eventId;
    }

    @Override
    public void bindEventOnly(ScheduleEventInfo eventInfo) {
        eventId = eventInfo.getId();
        spinnerType.setSelection(eventInfo.getOption());
        seekBarVolume.setProgress(eventInfo.getAmount());
        switchVibrate.setChecked(eventInfo.isVibrate());
        sundayToggle.setChecked(isSunday(eventInfo.getDays()));
        mondayToggle.setChecked(isMonday(eventInfo.getDays()));
        tuesdayToggle.setChecked(isTuesday(eventInfo.getDays()));
        wednesdayToggle.setChecked(isWednesday(eventInfo.getDays()));
        thursdayToggle.setChecked(isThursday(eventInfo.getDays()));
        fridayToggle.setChecked(isFriday(eventInfo.getDays()));
        saturdayToggle.setChecked(isSaturday(eventInfo.getDays()));
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, eventInfo.getHour());
        calendar.set(Calendar.MINUTE, eventInfo.getMinute());
        String formattedDate = format.format(calendar.getTime());
        timePicker.setText(formattedDate);
        switchMute.setChecked(eventInfo.isRepeatWeekly());
    }

    @Override
    public boolean isEditMode() {
        return isNotEmpty(getEventId());
    }

    @Override
    public void showDeleteTextView() {
        tvEditScheduleDelete.setVisibility(View.VISIBLE);
        tvEditScheduleDelete.setOnClickListener(v -> editSchedulePresenter.onDeleteClicked());
    }

    @Override
    public void displayDeleteConfirmation() {
        confirmDelete();
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @OnItemSelected(R.id.spinner_type)
    public void onOptionSelected() {
        editSchedulePresenter.onOptionChanged(spinnerType.getSelectedItemPosition());
    }

//    public void onAmountChanged(int level) {
//    }

    @OnCheckedChanged(R.id.switch_mute)
    public void onMuteToggleClicked() {
        editSchedulePresenter.onMuteChanged(switchMute.isChecked(), seekBarVolume.getProgress());
    }

    @OnCheckedChanged(R.id.switch_vibrate)
    public void onVibrateToggleClicked() {
        editSchedulePresenter.onVibrateChanged(switchVibrate.isChecked());
    }

    @OnCheckedChanged(R.id.toggle_sunday)
    public void onSundayToggleClicked() {
        editSchedulePresenter.onSundayChanged(sundayToggle.isChecked());
    }

    @OnCheckedChanged(R.id.toggle_monday)
    public void onMondayToggleClicked() {
        editSchedulePresenter.onMondayChanged(mondayToggle.isChecked());
    }

    @OnCheckedChanged(R.id.toggle_tuesday)
    public void onTuesdayToggleClicked() {
        editSchedulePresenter.onTuesdayChanged(tuesdayToggle.isChecked());
    }

    @OnCheckedChanged(R.id.toggle_wednesday)
    public void onWednesdayToggleClicked() {
        editSchedulePresenter.onWednesdayChanged(wednesdayToggle.isChecked());
    }

    @OnCheckedChanged(R.id.toggle_thursday)
    public void onThursdayToggleClicked() {
        editSchedulePresenter.onThursdayChanged(thursdayToggle.isChecked());
    }

    @OnCheckedChanged(R.id.toggle_friday)
    public void onFridayToggleClicked() {
        editSchedulePresenter.onFridayChanged(fridayToggle.isChecked());
    }

    @OnCheckedChanged(R.id.toggle_saturday)
    public void onSaturdayToggleClicked() {
        editSchedulePresenter.onSaturdayChanged(saturdayToggle.isChecked());
    }

//    public void onTimeSelected(int hour, int minute) {
//        editSchedulePresenter.onTimeChanged(hour, minute);
//    }

    @OnCheckedChanged(R.id.switch_repeat_weekly)
    public void onRepeatWeeklyToggleClicked() {
        editSchedulePresenter.onRepeatWeeklyChanged(switchRepeatWeekly.isChecked());
    }
}
