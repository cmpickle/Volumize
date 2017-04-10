package com.cmpickle.volumize.view.edit;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/9/2017.
 */

public interface EditView {
    void enableSaveButton();

    void disableSaveButton();

    void confirmCancel();

    void confirmDelete();
}
