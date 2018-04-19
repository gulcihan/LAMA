/*
 * Copyright 2003-2017 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */
package com.example.buyukdemircioglug.landslidealert.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public final class KeyboardUtil {

    /**
     * Private constructor for util class.
     */
    private KeyboardUtil() {
    }

    /**
     * Hides the soft keyboard from screen
     *
     * @param view Usually the EditText, but in dynamically  layouts you should pass the layout
     * instead of the EditText
     * @return true, if keyboard has been hidden, otherwise false (i.e. the keyboard was not displayed
     * on the screen or no Softkeyboard because device has hardware keyboard)
     */
    public static boolean hideKeyboard(View view) {

        if (view == null) {
            throw new NullPointerException("View is null!");
        }

        try {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

            if (imm == null) {
                return false;
            }

            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
