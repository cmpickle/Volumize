package com.cmpickle.volumize.view.schedule.edit;


import com.cmpickle.volumize.data.dto.ScheduleEventInfo;
import com.cmpickle.volumize.domain.ScheduleEventService;
import com.cmpickle.volumize.view.edit.EditPresenter;
import com.cmpickle.volumize.view.util.DayUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import icepick.State;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/9/2017.
 */

public class EditSchedulePresenter extends EditPresenter {

    private final ScheduleEventService eventService;

    @State
    ScheduleEventInfo oldEvent;

    @State
    ScheduleEventInfo newEvent;

    EditScheduleView editScheduleView;
    EditScheduleRouter editScheduleRouter;

    @Inject
    public EditSchedulePresenter(ScheduleEventService scheduleEventService) {
        this.eventService = scheduleEventService;
    }

    public void setView(EditScheduleView view) {
        super.setView(view);
        this.editScheduleView = view;
    }

    public void onViewResumed() {
        updateMuteChecked();
    }

    public void setScheduleRouter(EditScheduleRouter editScheduleRouter) {
        super.setEditRouter(editScheduleRouter);
        this.editScheduleRouter = editScheduleRouter;
    }

    @Override
    public void onAttemptSave() {
        //save event to database
        eventService.saveEvent(newEvent);

        editScheduleRouter.leave();
    }

    public void onOptionChanged(int option) {
        if(newEvent != null) {
            newEvent.setOption(option);
        }
    }

    public void onVolumeChanged(int amount) {
        if(newEvent != null) {
            newEvent.setAmount(amount);
            editScheduleView.setVolumeTV(String.valueOf(amount));
        }
    }

    public void onMuteChanged(boolean checked, int amount) {
        if(newEvent != null) {
            if(checked) {
                newEvent.setAmount(0);
            } else {
                newEvent.setAmount(amount);
            }
            editScheduleView.updateMuteView();
        }
    }

    public void onVibrateChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setVibrate(checked);
        }
    }

    public void onSundayChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setDays(DayUtil.setFlagSunday(checked, newEvent.getDays()));
        }
    }

    public void onMondayChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setDays(DayUtil.setFlagMonday(checked, newEvent.getDays()));
        }
    }

    public void onTuesdayChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setDays(DayUtil.setFlagTuesday(checked, newEvent.getDays()));
        }
    }

    public void onWednesdayChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setDays(DayUtil.setFlagWednesday(checked, newEvent.getDays()));
        }
    }

    public void onThursdayChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setDays(DayUtil.setFlagThursday(checked, newEvent.getDays()));
        }
    }

    public void onFridayChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setDays(DayUtil.setFlagFriday(checked, newEvent.getDays()));
        }
    }

    public void onSaturdayChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setDays(DayUtil.setFlagSaturday(checked, newEvent.getDays()));
        }
    }

    public void onRepeatWeeklyChanged(boolean checked) {
        if(newEvent != null) {
            newEvent.setRepeatWeekly(checked);
        }
    }

    public void onViewCreated() {
        if(newEvent != null) {
            if(editScheduleView.isEditMode()) {
                editScheduleView.showDeleteTextView();
            }
        } else {
            if(editScheduleView.isEditMode()) {
                loadEvent();
            } else {
                oldEvent = new ScheduleEventInfo();
                editScheduleView.bindEventOnly(oldEvent);
                if (editScheduleView.getEventId() == null) {
                    newEvent = new ScheduleEventInfo(oldEvent);
                }
            }
        }
        onEnteredData();
    }

    private void loadEvent() {
        oldEvent = eventService.getEventInfo(editScheduleView.getEventId());
        editScheduleView.bindEventOnly(oldEvent);
        newEvent = new ScheduleEventInfo(oldEvent);

        editScheduleView.showDeleteTextView();
    }

    public void onTimePickerClicked() {
        if(newEvent != null) {
            editScheduleView.openTimePicker(newEvent.getHour(), newEvent.getMinute());
        } else {
            editScheduleView.openTimePicker(oldEvent.getHour(), oldEvent.getMinute());
        }
    }

    public void onTimePicked(int hourOfDay, int minute) {
        if(newEvent != null) {
            newEvent.setHour(hourOfDay);
            newEvent.setMinute(minute);
            SimpleDateFormat format = new SimpleDateFormat("h:mm a");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH));
            calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            String formattedDate = format.format(calendar.getTime());
            editScheduleView.onTimePicked(formattedDate);
        }
    }

    public void updateMuteChecked() {
        editScheduleView.updateMuteView();
    }

    public void onDeleteClicked() {
        editScheduleView.displayDeleteConfirmation();
    }

    @Override
    public void onDeleteConfirmed() {
        eventService.deleteEvent(oldEvent.getId());
        editScheduleRouter.leave();
    }
}
