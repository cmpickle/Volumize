package com.cmpickle.volumize.view.profile;

import com.cmpickle.volumize.view.BasePresenter;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/7/2017.
 */

public class ProfilePresenter extends BasePresenter<ProfileView> {

    ProfileView profileView;
    ProfileRouter profileRouter;

    @Override
    protected void setView(ProfileView profileView) {
        this.profileView = profileView;
    }

    protected void setRouter(ProfileRouter profileRouter) {
        this.profileRouter = profileRouter;
    }

    public void addProfileClicked() {
        profileRouter.moveToEditProfilePage();
    }
}
