package com.cmpickle.volumize.view.schedule.edit;

import com.cmpickle.volumize.view.edit.EditPresenter;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/9/2017.
 */

public class EditSchedulePresenter extends EditPresenter {

    EditScheduleView editScheduleView;
    EditScheduleRouter editScheduleRouter;

    public void setView(EditScheduleView view) {
        super.setView(view);
        this.editScheduleView = view;
    }

    public void setScheduleRouter(EditScheduleRouter editScheduleRouter) {
        super.setEditRouter(editScheduleRouter);
        this.editScheduleRouter = editScheduleRouter;
    }

    @Override
    public void onAttemptSave() {

    }
}
