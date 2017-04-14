package com.cmpickle.volumize.view.alerts;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/11/2017.
 */

public interface AlertListener {

    void onAlertRightButton(AlertDialogParams params);

    void onAlertLeftButton(AlertDialogParams params);

    void onAlertDismissed(AlertDialogParams params);
}
