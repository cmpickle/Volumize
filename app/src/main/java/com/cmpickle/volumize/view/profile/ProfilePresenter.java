package com.cmpickle.volumize.view.profile;

import com.cmpickle.volumize.view.BasePresenter;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/7/2017.
 */

public class ProfilePresenter extends BasePresenter<ProfileView> {

    ProfileView profileView;

    @Override
    protected void setView(ProfileView View) {
        this.profileView = profileView;
    }
}
