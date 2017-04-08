package com.cmpickle.volumize.view;

import android.os.Bundle;
import android.view.View;

import icepick.Icepick;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/1/2017.
 */

public abstract class BasePresenter<V> {

    protected abstract void setView(V View);

    void saveInstanceState(Bundle out) {
        Icepick.saveInstanceState(this, out);
    }

    void restoreInstanceState(Bundle in) {
        Icepick.restoreInstanceState(this, in);
    }
}
