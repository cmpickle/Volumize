package com.cmpickle.volumize.view.volumeadjust;

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

import icepick.State;


/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

public class VolumeAdjustFragment extends BaseFragment implements VolumeAdjustView {

    @Inject
    VolumeAdjustPresenter volumeAdjustPresenter;

    public VolumeAdjustFragment() {
//        Injector.get().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_volume_adjust, container, false);
    }

    @Override
    protected void onSetViewOnPresenter() {
//        volumeAdjustPresenter.setView(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return volumeAdjustPresenter;
    }
}
