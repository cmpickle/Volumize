package com.cmpickle.volumize.view.alerts;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;

import icepick.Icepick;
import icepick.State;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/11/2017.
 */

public class AlertUtil implements AlertListener {

    @State
    AlertDialogParams alertDialogParams;

    @State
    Integer errorTextResourceId;

    private AlertDialog alertDialog;

    private Alerts alerts;
    private Context context;
    private AlertListener alertListener;

    public AlertUtil(Alerts alerts, Context context, AlertListener alertListener) {
        this.alerts = alerts;
        this.context = context;
        this.alertListener = alertListener;
    }

    @Override
    public void onAlertRightButton(AlertDialogParams params) {
        if(alertListener != null) {
            alertListener.onAlertRightButton(params);
        }
    }

    @Override
    public void onAlertLeftButton(AlertDialogParams params) {
        if(alertListener != null) {
            alertListener.onAlertLeftButton(params);
        }
    }

    @Override
    public void onAlertDismissed(AlertDialogParams params) {
        alertDialogParams = null;
        errorTextResourceId = null;

        if(alertListener != null) {
            alertListener.onAlertDismissed(params);
        }
    }

    public void showError(@StringRes int errorTextResourceId) {
        this.errorTextResourceId = errorTextResourceId;
        alertDialog = alerts.showError(context, this, errorTextResourceId);
    }

    public void showAlert(AlertDialogParams params) {
        this.alertDialogParams = params;
        alertDialog = alerts.showAlert(context, this, params);
    }

    public void restore(Bundle savedInstanceState) {
        Icepick.restoreInstanceState(this, savedInstanceState);

        if(alertDialogParams != null) {
            showAlert(alertDialogParams);
        } else if(errorTextResourceId != null) {
            showError(errorTextResourceId);
        }
    }

    public void save(Bundle outState) {
        if(alertDialog != null) {
            Icepick.saveInstanceState(this, outState);
        }
    }

    public void destroy() {
        if(alertDialog != null) {
            alertDialog.dismiss();
        }

        context = null;
        alertListener = null;
    }
}
