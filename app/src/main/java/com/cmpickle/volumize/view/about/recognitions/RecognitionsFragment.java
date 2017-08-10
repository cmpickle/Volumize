package com.cmpickle.volumize.view.about.recognitions;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.view.BaseFragment;
import com.cmpickle.volumize.view.BasePresenter;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/17/2017.
 */

public class RecognitionsFragment extends BaseFragment implements RecognitionsView {

    @Inject
    RecognitionsPresenter recognitionsPresenter;

    public RecognitionsFragment() {
        Injector.get().inject(this);
    }

    @Override
    protected void onSetViewAndRouterOnPresenter() {
        recognitionsPresenter.setView(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return recognitionsPresenter;
    }
}
