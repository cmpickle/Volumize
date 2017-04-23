package com.cmpickle.volumize.view.schedule.edit;

import com.cmpickle.volumize.data.dto.ScheduleEventInfo;
import com.cmpickle.volumize.view.edit.EditView;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/9/2017.
 */

public interface EditScheduleView extends EditView {

    void setVolumeTV(String level);

    void openTimePicker(int hour, int minute);

    void onTimePicked(String time);

    void updateMuteView();

//    int getOption();
//
//    int getAmount();
//
//    boolean isVibrate();
//
//    boolean isRepeatWeekly();
//
//    int getDays();

    String getEventId();

    void bindEventOnly(ScheduleEventInfo eventInfo);

    boolean isEditMode();

    void showDeleteTextView();

    void displayDeleteConfirmation();
}
