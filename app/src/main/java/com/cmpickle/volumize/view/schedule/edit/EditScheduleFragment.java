package com.cmpickle.volumize.view.schedule.edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.BasePresenter;
import com.cmpickle.volumize.view.edit.EditFragment;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/9/2017.
 */

public class EditScheduleFragment extends EditFragment implements EditScheduleView {

    @Inject
    EditSchedulePresenter editSchedulePresenter;

    public EditScheduleFragment() {
        Injector.get().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_schedule, container, false);
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
}
