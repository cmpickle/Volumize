package com.cmpickle.volumize.view.profile.edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.BasePresenter;
import com.cmpickle.volumize.view.edit.EditFragment;

import javax.inject.Inject;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/16/2017.
 */

public class EditProfileFragment extends EditFragment implements EditProfileView {

    @Inject
    EditProfilePresenter editProfilePresenter;

    public EditProfileFragment() {
        Injector.get().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false);
    }

    @Override
    protected void onSetViewAndRouterOnPresenter() {
        editProfilePresenter.setView(this);
        editProfilePresenter.setProfileRouter((EditProfileRouter) getActivity());
    }

    @Override
    protected BasePresenter getPresenter() {
        return editProfilePresenter;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
