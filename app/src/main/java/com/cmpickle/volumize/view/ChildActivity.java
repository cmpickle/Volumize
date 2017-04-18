package com.cmpickle.volumize.view;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import com.cmpickle.volumize.R;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/17/2017.
 */

public abstract class ChildActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = (Toolbar) findViewById(R.id.child_toolbar);

        toolbar.setTitle(getToolbarTitle());
        setSupportActionBar(toolbar);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_child;
    }

    public void setToolbarColor(@ColorRes int colorId) {
        toolbar.setBackgroundColor(ContextCompat.getColor(this, colorId));
    }

    @StringRes
    protected abstract int getToolbarTitleId();

    protected String getToolbarTitle() {
        return getString(getToolbarTitleId());
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }
}
