package com.cmpickle.volumize.view.schedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.BaseFragment;
import com.cmpickle.volumize.view.BasePresenter;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/7/2017.
 */

public class ScheduleFragment extends BaseFragment implements ScheduleView {

    @Inject
    SchedulePresenter schedulePresenter;

    public ScheduleFragment() {
        Injector.get().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ScheduleActivity activity = (ScheduleActivity) getActivity();
        Toolbar toolbar = activity.getToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openNavigationDrawer();
            }
        });
    }

    @Override
    protected void onSetViewOnPresenter() {
        schedulePresenter.setView(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return schedulePresenter;
    }
}
