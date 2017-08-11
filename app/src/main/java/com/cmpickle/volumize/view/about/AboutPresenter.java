package com.cmpickle.volumize.view.about;

import com.cmpickle.volumize.view.BasePresenter;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/7/2017.
 */

public class AboutPresenter extends BasePresenter<AboutView> {

    AboutView aboutView;
    AboutRouter aboutRouter;

    @Override
    protected void setView(AboutView aboutView) {
        this.aboutView = aboutView;
    }

    protected void setRouter(AboutRouter aboutRouter) {
        this.aboutRouter = aboutRouter;
    }

    public void initialize() {
        aboutView.buildFragment();
        aboutView.setVersionNumber();
    }

    public void moveToRateAppPage() {
        aboutRouter.moveToRateAppPage();
    }

    public void moveToHomepage() {
        aboutRouter.moveToHomepage();
    }

    public  void moveToFeatureRequests() {
        aboutRouter.moveToFeatureRequests();
    }

    public void moveToFeedback() {
        aboutRouter.moveToFeedback();
    }

    public void moveToRecognitionsPage() {
        aboutRouter.moveToRecognitoinsPage();
    }
}
