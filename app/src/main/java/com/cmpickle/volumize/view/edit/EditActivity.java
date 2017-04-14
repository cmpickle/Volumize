package com.cmpickle.volumize.view.edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;

import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.BaseActivity;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/9/2017.
 */

public abstract class EditActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = (Toolbar) findViewById(R.id.edit_toolbar);

        toolbar.setTitle(getToolbarTitle());
        setSupportActionBar(toolbar);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_edit;
    }

    @StringRes
    protected abstract int getToolbarTitleId();

    protected String getToolbarTitle() {
        return getString(getToolbarTitleId());
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    private EditFragment getEditFragment() {
        return (EditFragment) getFragment();
    }
}
