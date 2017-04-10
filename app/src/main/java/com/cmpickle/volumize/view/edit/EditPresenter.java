package com.cmpickle.volumize.view.edit;

import android.support.annotation.CallSuper;

import com.cmpickle.volumize.view.BasePresenter;

import icepick.State;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/9/2017.
 */

public abstract class EditPresenter extends BasePresenter<EditView> {

    @State
    boolean hasEnteredData;

    private EditView editView;
    private EditRouter editRouter;

    @CallSuper
    @Override
    protected void setView(EditView view) {
        this.editView = view;
    }

    protected EditRouter getEditRouter() {
        return editRouter;
    }

    @CallSuper
    public void setEditRouter(EditRouter editRouter) {
        this.editRouter = editRouter;
    }

    public void clearEnteredData() {
        hasEnteredData = false;
        updateSaveButton();
    }

    protected boolean hasEnteredData() {
        return hasEnteredData;
    }

    protected boolean isSaveable() {
        return hasEnteredData();
    }

    public void onAttemptCancel() {
        if(hasEnteredData) {
            editView.confirmCancel();
        } else {
            editRouter.leave();
        }
    }

    public abstract void onAttemptSave();

    public void onBackPressed() {
        onAttemptCancel();
    }

    public void onCancelConfirmed() {
        editRouter.leave();
    }

    public void onCreateOptionsMenuFinished() {
        updateSaveButton();
    }

    public void onDeleteConfirmed() {

    }

    public void onEnteredData() {
        hasEnteredData = true;
        updateSaveButton();
    }

    private void updateSaveButton() {
        if(isSaveable()) {
            editView.enableSaveButton();
        } else {
            editView.disableSaveButton();
        }
    }
}
