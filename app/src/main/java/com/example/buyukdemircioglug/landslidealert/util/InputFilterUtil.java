package com.example.buyukdemircioglug.landslidealert.util;

import android.text.InputFilter;
import android.widget.EditText;

public final class InputFilterUtil {

    /**
     * Private constructor for util class.
     */
    private InputFilterUtil() {
    }

    /**
     * Add input filter(s) on EditText. If it already has input filter(s) new ones will be merged.
     *
     * @param editText    EditText that may already have an input filter.
     * @param inputFilters InputFilter(s) to be added on EditText.
     */
    public static void addInputFilter(EditText editText, InputFilter... inputFilters) {

        if (inputFilters == null || inputFilters.length == 0) {
            return;
        }

        final InputFilter[] currentFilters = editText.getFilters();
        final InputFilter[] finalFilters = new InputFilter[currentFilters.length + inputFilters.length];
        System.arraycopy(currentFilters, 0, finalFilters, 0, currentFilters.length);
        System.arraycopy(inputFilters, 0, finalFilters, currentFilters.length, inputFilters.length);
        editText.setFilters(finalFilters);

    }

}
