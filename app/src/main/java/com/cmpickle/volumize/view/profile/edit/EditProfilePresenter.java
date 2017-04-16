package com.cmpickle.volumize.view.profile.edit;

import com.cmpickle.volumize.view.edit.EditPresenter;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/16/2017.
 */

public class EditProfilePresenter extends EditPresenter {

    EditProfileView editProfileView;
    EditProfileRouter editProfileRouter;

    protected void setView(EditProfileView view) {
        super.setView(view);
        this.editProfileView = view;
    }

    protected void setProfileRouter(EditProfileRouter editProfileRouter) {
        super.setEditRouter(editProfileRouter);
        this.editProfileRouter = editProfileRouter;
    }

    @Override
    public void onAttemptSave() {

    }
}
