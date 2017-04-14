package com.cmpickle.volumize.view.edit;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.BaseFragment;
import com.cmpickle.volumize.view.alerts.AlertDialogParams;
import com.cmpickle.volumize.view.alerts.AlertType;

import static com.cmpickle.volumize.view.alerts.AlertType.CONFIRM_DISCARD;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/9/2017.
 */

public abstract class EditFragment extends BaseFragment implements EditView {

    private MenuItem saveMenuItem;

    @Override
    public void confirmDelete() {
        AlertDialogParams params = new AlertDialogParams(null, getDeleteTitleId());
        params.setRightButtonTextResourceId(R.string.common_delete);
        params.setRightButtonColorResourceId(R.color.delete_red);
        params.setLeftButtonTextResourceId(R.string.common_cancel);
        params.setType(AlertType.CONFIRM_DELETE);
        showAlert(params);
    }

    @CallSuper
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        saveMenuItem = menu.findItem(R.id.menu_item_check);
        getEditPresenter().onCreateOptionsMenuFinished();
    }

    public int getDeleteTitleId() {
        return R.string.common_delete_title;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_item_check) {
            getEditPresenter().onAttemptSave();
        } else if(item.getItemId() == android.R.id.home) {
            getEditPresenter().onAttemptCancel();
        }

        return true;
    }

    @Override
    public void enableSaveButton() {
        if(saveMenuItem != null && !saveMenuItem.isEnabled()) {
            saveMenuItem.setEnabled(true);
        }
    }

    @Override
    public void disableSaveButton() {
        if(saveMenuItem != null && saveMenuItem.isEnabled()) {
            saveMenuItem.setEnabled(false);
        }
    }

    @Override
    public void confirmCancel() {
        AlertDialogParams params = new AlertDialogParams(null, R.string.confirm_cancel_subtitle);
        params.setRightButtonTextResourceId(R.string.common_discard);
        params.setLeftButtonTextResourceId(R.string.common_keep_editing);
        params.setType(CONFIRM_DISCARD);
        showAlert(params);
    }

    @Override
    public void onAlertRightButton(AlertDialogParams params) {
        AlertType type = params.getType();
        if (type == null) {
            super.onAlertRightButton(params);
            return; // showError alerts don't have a type
        }
        switch (type) {
            case CONFIRM_DELETE:
                getEditPresenter().onDeleteConfirmed();
                break;
            case CONFIRM_DISCARD:
                getEditPresenter().onCancelConfirmed();
                break;
            default:
                super.onAlertRightButton(params);
        }
    }

    public EditPresenter getEditPresenter() {
        return (EditPresenter) getPresenter();
    }

    public void onBackPressed() {
        getEditPresenter().onBackPressed();
    }

    public void onEnteredData() {
        getEditPresenter().onEnteredData();
    }
}
