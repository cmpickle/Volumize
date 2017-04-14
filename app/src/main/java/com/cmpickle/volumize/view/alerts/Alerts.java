package com.cmpickle.volumize.view.alerts;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.cmpickle.volumize.R;
import com.cmpickle.volumize.view.util.ViewUtil;

import javax.inject.Inject;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/11/2017.
 */

public class Alerts {

    private  static final int DEFAULT_WIDTH = 300;

    private ViewUtil viewUtil;

    @Inject
    public Alerts(ViewUtil viewUtil) {
        this.viewUtil = viewUtil;
    }

    AlertDialog showAlert(Context context, final AlertListener alertListener, final AlertDialogParams params) {

        final AlertDialog.Builder helpBuilder = new AlertDialog.Builder(context);

        @SuppressLint("InflateParams") //no parent for AlertDialog
        final View alertView = LayoutInflater.from(context).inflate(R.layout.alert, null);

        helpBuilder.setView(alertView);

        final AlertDialog alertDialog = helpBuilder.create();

        alertDialog.getWindow().setDimAmount(0.6f);
        alertDialog.getWindow().addFlags(FLAG_DIM_BEHIND);

        alertDialog.setCanceledOnTouchOutside(params.isCancelOnTapOutside());
        alertDialog.setCancelable(params.isCancelable());

        TextView title = (TextView) alertView.findViewById(R.id.tv_alert_title);
        TextView subtitle = (TextView) alertView.findViewById(R.id.tv_alert_subtitle);
        TextView btnLeft = (TextView) alertView.findViewById(R.id.btn_left);
        TextView btnRight = (TextView) alertView.findViewById(R.id.btn_right);

        //Title
        String titleText = null;
        if(params.getTitleResourceId() != null) {
            titleText = context.getString(params.getTitleResourceId());
        }
        viewUtil.setTextOrHide(title, titleText);

        //Subtitle
        String subTitleText = null;
        if(params.getSubTitleResourceId() != null) {
            subTitleText = context.getString(params.getSubTitleResourceId());
        }
        viewUtil.setTextOrHide(subtitle, subTitleText);

        //Right button always show
        btnRight.setVisibility(View.VISIBLE);
        if(params.getRightButtonTextResourceId() != null) {
            btnRight.setText(context.getString(params.getRightButtonTextResourceId()));
        }
        if(params.getRightButtonColorResourceId() != null) {
            btnRight.setTextColor(ContextCompat.getColor(context, params.getRightButtonColorResourceId()));
        }
        btnRight.setOnClickListener(v -> {
            alertDialog.cancel();
            alertListener.onAlertRightButton(params);
        });

        //Left button
        if(params.getLeftButtonTextResourceId() != null) {
            btnLeft.setText(context.getString(params.getLeftButtonTextResourceId()));
            if (params.getLeftButtonColorResourceId() != null) {
                btnRight.setTextColor(ContextCompat.getColor(context, params.getLeftButtonColorResourceId()));
            }
            btnLeft.setVisibility(View.VISIBLE);
            btnLeft.setOnClickListener(v -> {
                alertDialog.cancel();
                alertListener.onAlertLeftButton(params);
            });
        } else {
            btnLeft.setVisibility(View.INVISIBLE);
        }

        //Dismiss listener
        alertDialog.setOnDismissListener(dialogInterface -> alertListener.onAlertDismissed(params));

        //The use of metrics.density is to convert the DP to PX. SetAttributes only accepts PX.
        DisplayMetrics metrics = new DisplayMetrics();
        alertDialog.getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        if(params.getWidth() == null) {
            lp.width = (int) (DEFAULT_WIDTH * metrics.density);
        } else {
            lp.width = (int) (params.getWidth() * metrics.density);
        }
        if(params.getHeight() == null) {
            lp.height = WRAP_CONTENT;
        } else {
            lp.height = (int) (params.getHeight() * metrics.density);
        }

        alertDialog.show();
        alertDialog.getWindow().setAttributes(lp);
        return alertDialog;
    }

    AlertDialog showError(Context context, AlertListener alertListener, @StringRes int errorTextResourceId) {
        AlertDialogParams alertDialogParams = new AlertDialogParams(R.string.common_error, errorTextResourceId);
        return showAlert(context, alertListener, alertDialogParams);
    }
}
