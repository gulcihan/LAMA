/*
 * Copyright 2003-2017 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */
package com.example.buyukdemircioglug.landslidealert.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public final class KeyboardUtil {

    private static final int POSSIBLE_KEYBOARD_LENGTH = 100;

    /**
     * Private constructor for util class.
     */
    private KeyboardUtil() {
    }

    /**
     * Utility method for showing keyboard.
     *
     * @param view which has keyboard focus
     */
    public static void showKeyboard(View view) {
        final InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * Utility method for hiding keyboard.
     *
     * @param view which has keyboard focus
     */
    public static void hideKeyboard(View view) {
        final InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Utility method for hiding keyboard.
     *
     * @param activity which as keyboard focus
     */
    public static void hideKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        if (activity.getCurrentFocus() != null) {
            final InputMethodManager inputMethodManager = (InputMethodManager)
                    activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * This utility method toggles the input method window display.
     * If the input window is already displayed, it gets hidden.
     * If not the input window will be displayed.
     *
     * @param view which has keyboard focus
     */
    public static void toggleKeyboard(View view) {

        final InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * Checks if keyboard is visible.
     *
     * @param rootView root view of screen
     * @return true if keyboard is open
     */
    public static boolean isKeyboardOpen(View rootView) {
        final Rect r = new Rect();

        //r will be populated with the coordinates of your view that area still visible.
        rootView.getWindowVisibleDisplayFrame(r);

        final int heightDiff = rootView.getRootView().getHeight() - (r.bottom - r.top);

        // if more than 100 pixels, its probably a keyboard...
        return heightDiff > POSSIBLE_KEYBOARD_LENGTH;
    }

}
