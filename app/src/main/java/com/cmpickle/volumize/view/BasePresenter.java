package com.cmpickle.volumize.view;

import android.os.Bundle;

import icepick.Icepick;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/1/2017.
 */

public abstract class BasePresenter {

    protected abstract void setView(Object o);

    void saveInstanceState(Bundle out) {
        Icepick.saveInstanceState(this, out);
    }

    void restoreInstanceState(Bundle in) {
        Icepick.restoreInstanceState(this, in);
    }
}
