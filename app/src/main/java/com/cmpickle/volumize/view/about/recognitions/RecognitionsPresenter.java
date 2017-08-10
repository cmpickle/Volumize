package com.cmpickle.volumize.view.about.recognitions;

import com.cmpickle.volumize.view.BasePresenter;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/17/2017.
 */

public class RecognitionsPresenter extends BasePresenter<RecognitionsView> {

    RecognitionsView recognitionsView;

    @Override
    protected void setView(RecognitionsView view) {
        this.recognitionsView = view;
    }
}
