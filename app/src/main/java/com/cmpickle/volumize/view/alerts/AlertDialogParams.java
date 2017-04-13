package com.cmpickle.volumize.view.alerts;

import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/11/2017.
 */

public class AlertDialogParams implements Serializable {
    @StringRes private Integer titleResourceId;
    @StringRes private Integer subTitleResourceId;
    @StringRes private Integer rightButtonTextResourceId;
    @StringRes private Integer leftButtonTextResourceId;

    @ColorRes private Integer rightButtonColorResourceId;
    @ColorRes private Integer leftButtonColorResourceId;

    private Integer width;
    private Integer height;

    private boolean cancelOnTapOutside = true;
    private boolean cancelable = true;

    private AlertType type;

    public AlertDialogParams(@StringRes Integer titleResourceId, @StringRes Integer subTitleResourceId) {
        this.titleResourceId = titleResourceId;
        this.subTitleResourceId = subTitleResourceId;
    }

    public Integer getTitleResourceId() {
        return titleResourceId;
    }

    public Integer getSubTitleResourceId() {
        return subTitleResourceId;
    }

    public Integer getRightButtonTextResourceId() {
        return rightButtonTextResourceId;
    }

    public void setRightButtonTextResourceId(Integer rightButtonTextResourceId) {
        this.rightButtonTextResourceId = rightButtonTextResourceId;
    }

    public Integer getLeftButtonTextResourceId() {
        return leftButtonTextResourceId;
    }

    public void setLeftButtonTextResourceId(Integer leftButtonTextResourceId) {
        this.leftButtonTextResourceId = leftButtonTextResourceId;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public boolean isCancelOnTapOutside() {
        return cancelOnTapOutside;
    }

    public void setCancelOnTapOutside(boolean cancelOnTapOutside) {
        this.cancelOnTapOutside = cancelOnTapOutside;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public AlertType getType() {
        return type;
    }

    public void setType(AlertType type) {
        this.type = type;
    }

    public Integer getRightButtonColorResourceId() {
        return rightButtonColorResourceId;
    }

    public void setRightButtonColorResourceId(Integer rightButtonColorResourceId) {
        this.rightButtonColorResourceId = rightButtonColorResourceId;
    }

    public Integer getLeftButtonColorResourceId() {
        return leftButtonColorResourceId;
    }

    public void setLeftButtonColorResourceId(Integer leftButtonColorResourceId) {
        this.leftButtonColorResourceId = leftButtonColorResourceId;
    }

    public boolean isType(AlertType type) {
        return Objects.equals(this.type, type);
    }
}
