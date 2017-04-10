package com.cmpickle.volumize.view.edit;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.BaseFragment;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/9/2017.
 */

public abstract class EditFragment extends BaseFragment implements EditView {

    private MenuItem saveMenuItem;

    @Override
    public void confirmDelete() {
//        AlertDialogParams params
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
