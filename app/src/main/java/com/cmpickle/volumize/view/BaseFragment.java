package com.cmpickle.volumize.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/1/2017.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);

        if(savedInstanceState != null) {
            getPresenter().restoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        onSetViewOnPresenter();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();

        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
        if(getPresenter() != null) {
            getPresenter().saveInstanceState(outState);
        }
    }

    @Override
    public void onDestroy() {
        if(getPresenter() != null) {
//            getPresenter().terminate();
        }
        super.onDestroy();
    }

    protected abstract void onSetViewOnPresenter();

    protected abstract BasePresenter getPresenter();
}
