package com.cmpickle.volumize.view.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmpickle.volumize.Inject.Injector;
import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.BaseFragment;
import com.cmpickle.volumize.view.BasePresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/7/2017.
 */

public class ProfileFragment extends BaseFragment implements ProfileView {

    @Inject
    ProfilePresenter profilePresenter;

    @BindView(R.id.fab_profile)
    FloatingActionButton fabProfile;

    public ProfileFragment() {
        Injector.get().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ProfileActivity activity = (ProfileActivity) getActivity();
        Toolbar toolbar = activity.getToolbar();
        toolbar.setNavigationOnClickListener(v -> activity.openNavigationDrawer());

        fabProfile.setOnClickListener(v -> profilePresenter.addProfileClicked());
    }

    @Override
    protected void onSetViewAndRouterOnPresenter() {
        profilePresenter.setView(this);
        profilePresenter.setRouter((ProfileRouter) getActivity());
    }

    @Override
    protected BasePresenter getPresenter() {
        return profilePresenter;
    }
}
