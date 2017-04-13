package com.cmpickle.volumize.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.alerts.AlertDialogParams;
import com.cmpickle.volumize.view.alerts.AlertListener;
import com.cmpickle.volumize.view.alerts.AlertUtil;
import com.cmpickle.volumize.view.alerts.Alerts;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/1/2017.
 */

public abstract class BaseFragment extends Fragment implements AlertListener {

    @Inject
    Alerts alerts;

    AlertUtil alertUtil;

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);

        if(savedInstanceState != null) {
            getPresenter().restoreInstanceState(savedInstanceState);
        }

        onSetViewAndRouterOnPresenter();
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        alertUtil = new AlertUtil(alerts, getContext(), this);
        alertUtil.restore(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if(unbinder != null)
            unbinder.unbind();

        if(alertUtil != null)
            alertUtil.destroy();

        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
        if(getPresenter() != null) {
            getPresenter().saveInstanceState(outState);
        }
        alertUtil.save(outState);
    }

    @Override
    public void onDestroy() {
        if(getPresenter() != null) {
//            getPresenter().terminate();
        }
        super.onDestroy();
    }

    @Override
    public void onAlertLeftButton(AlertDialogParams params) {
        //do nothing by default
    }

    @Override
    public void onAlertRightButton(AlertDialogParams params) {
        //do nothing by default
    }

    @Override
    public void onAlertDismissed(AlertDialogParams params) {
        //do nothing by default
    }

    public void coinfirmCancel() {
        AlertDialogParams params = new AlertDialogParams(null, R.string.edit_schedule_confirm_cancel_subtitle);
        params.setRightButtonTextResourceId(R.string.common_discard);
        params.setLeftButtonTextResourceId(R.string.common_keep_editing);
        showAlert(params);
    }

    public void showError(@StringRes int errorTextResourceId) {
        alertUtil.showError(errorTextResourceId);
    }

    public void showAlert(AlertDialogParams params) {
        alertUtil.showAlert(params);
    }

    protected abstract void onSetViewAndRouterOnPresenter();

    protected abstract BasePresenter getPresenter();
}
