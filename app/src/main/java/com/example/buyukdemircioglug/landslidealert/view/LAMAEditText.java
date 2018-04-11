/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package com.example.buyukdemircioglug.landslidealert.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.util.AttributeSet;

import com.example.buyukdemircioglug.landslidealert.util.InputFilterUtil;


public class LAMAEditText extends AppCompatEditText {

    public LAMAEditText(Context context) {
        super(context);
    }

    public LAMAEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LAMAEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Add given input filters.
     * This method is not responsible to prevent filter and inputType clashes.
     * It merges given filters with the ones that already exist.
     *
     * @param filters input filters
     */
    public void addInputFilters(InputFilter... filters) {
        InputFilterUtil.addInputFilter(this, filters);
    }
}
