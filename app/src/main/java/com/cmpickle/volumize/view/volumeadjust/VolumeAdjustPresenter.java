package com.cmpickle.volumize.view.volumeadjust;

import com.cmpickle.volumize.view.BasePresenter;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/3/2017.
 */

public class VolumeAdjustPresenter extends BasePresenter {

    VolumeAdjustView volumeAdjustView;

    @Override
    protected void setView(Object o) {
        volumeAdjustView = (VolumeAdjustView) o;
    }
}
