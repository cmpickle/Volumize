package com.cmpickle.volumize.view.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.cmpickle.volumize.R;

import java.util.function.Consumer;

import javax.inject.Inject;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author Cameron Pickle
 *         Copyright (C) Cameron Pickle (cmpickle) on 4/11/2017.
 */

public class ViewUtil {
    private Resources resources;

    @Inject
    public ViewUtil(Resources resources) {
        this.resources = resources;
    }

    public void setTextOrHide(TextView textView, CharSequence text) {
        if (isNotBlank(text)) {
            textView.setText(text);
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

//    public ProgressDialog createProgressDialog(Context context) {
//        ProgressDialog progressDialog = new ProgressDialog(context);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
//        progressDialog.setIndeterminateDrawable(context.getDrawable(R.drawable.progress));
//        progressDialog.setCanceledOnTouchOutside(false);
//
//        return progressDialog;
//    }

//    public boolean isPhonePortrait(Context context) {
//        return context.getResources().getBoolean(R.bool.isPhonePortrait);
//    }

    public boolean isBottomSheetOverHalfScreen(View bottomSheet) {
        int halfHeight = bottomSheet.getHeight() / 2;
        // half * .25 to delay hiding/moving buttons a bit on slide to full
        return bottomSheet.getHeight() - bottomSheet.getTop() > halfHeight + (halfHeight * .25);
    }

//    public void addInsetLineDecorator(RecyclerView recyclerView, Context context) {
//        addInsetLineDecorator(recyclerView, context, null);
//    }

//    public void addInsetLineDecorator(RecyclerView recyclerView, Context context, DecorateDecider decoratorDecider) {
//        Drawable dividerDrawable = ContextCompat.getDrawable(context, R.drawable.preference_list_divider_material);
//        InsetDrawable insetDrawable = new InsetDrawable(dividerDrawable,
//                context.getResources().getDimensionPixelSize(R.dimen.person_icon_section_width), 0, 0, 0);
//        recyclerView.addItemDecoration(new DividerItemDecoration(insetDrawable, decoratorDecider));
//    }

//    public void addFullWidthLineDecorator(RecyclerView recyclerView, Context context, DecorateDecider decoratorDecider) {
//        Drawable dividerDrawable = ContextCompat.getDrawable(context, R.drawable.preference_list_divider_material);
//        recyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable, decoratorDecider));
//    }

    public void forEachChild(ViewGroup viewGroup, Consumer<View> action) {
        int length = viewGroup.getChildCount();
        for (int i = 0; i < length; i++) {
            action.accept(viewGroup.getChildAt(i));
        }
    }

    public <T extends View> void forEachChild(ViewGroup viewGroup, Class<T> type, Consumer<T> action) {
        int length = viewGroup.getChildCount();
        for (int i = 0; i < length; i++) {
            View child = viewGroup.getChildAt(i);
            if (type.isAssignableFrom(child.getClass())) {
                action.accept(type.cast(child));
            }
        }
    }

    public void makeClickable(View view) {
        makeClickableInternal(view, R.attr.selectableItemBackground);
    }

    public void makeClickableBorderless(View view) {
        makeClickableInternal(view, R.attr.selectableItemBackgroundBorderless);
    }

    public
    @ColorRes
    int getAccentColorResource(Context context) {
        return getResourceByAttr(R.attr.colorAccent, context);
    }

    public int getResourceByAttr(@AttrRes int attr, Context context) {
        int[] attrs = new int[]{attr};
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        int resourceId = typedArray.getResourceId(0, 0);
        typedArray.recycle();
        return resourceId;
    }

    private void makeClickableInternal(View view, @AttrRes int attr) {
        view.setClickable(true);
        view.setBackgroundResource(getResourceByAttr(attr, view.getContext()));
    }

    public void handleFocusChangedHint(boolean hasFocus, EditText editText, String focusedHint) {
        if (hasFocus && editText.getText().toString().length() == 0) {
            editText.setHint(focusedHint);
        } else {
            // This is because if you switch from the field when it is blank,
            // it will stay big because focus is already lost before the hint changes.
            if (editText.getText().toString().length() == 0) {
                editText.setText(".");
                editText.setText("");
            }
            editText.setHint("");
        }
    }

    public void handleTextChangedHint(CharSequence text, EditText editText, String focusedHint) {
        if ((text == null || text.toString().length() == 0) && editText.hasFocus()) {
            editText.setHint(focusedHint);
        } else {
            editText.setHint("");
        }
    }

    public void hideKeyboard(Context context, View rootView) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
    }

    public void showKeyboardForEditText(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public String getString(@StringRes int stringResourceId) {
        return resources.getString(stringResourceId);
    }
}
