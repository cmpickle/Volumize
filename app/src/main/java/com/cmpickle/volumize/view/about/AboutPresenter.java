package com.cmpickle.volumize.view.about;

import com.cmpickle.volumize.view.BasePresenter;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/7/2017.
 */

public class AboutPresenter extends BasePresenter<AboutView> {

    AboutView aboutView;

    @Override
    protected void setView(AboutView aboutView) {
        this.aboutView = aboutView;
    }

    public void initialize() {
        aboutView.buildFragment();
        aboutView.setVersionNumber();
    }
}
