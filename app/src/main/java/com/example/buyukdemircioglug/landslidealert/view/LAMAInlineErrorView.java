package com.example.buyukdemircioglug.landslidealert.view;

import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.TextView;

public interface LAMAInlineErrorView {

    int HIDDEN = 0;
    int VISIBLE = 1;

    /**
     * Set hint.
     *
     * @param text text
     */
    void setHint(String text);

    /**
     * Set text.
     *
     * @param text text
     */
    void setText(String text);

    /**
     * Set error text.
     *
     * @param text text
     */
    void setErrorText(String text);

    /**
     * Set error state.
     *
     * @param errored if true VISIBLE else GONE
     */
    void setErrorState(boolean errored);

    /**
     * Set input type.
     *
     * @param type type
     */
    void setInputType(int type);

    /**
     * Add text watcher.
     *
     * @param textWatcher text watcher
     */
    void addTextChangedListener(TextWatcher textWatcher);

    /**
     * Set OnEditorActionListener.
     *
     * @param onEditorActionListener listener
     */
    void setOnEditorActionListener(TextView.OnEditorActionListener onEditorActionListener);

    /**
     * Set ime options.
     *
     * @param imeOptions imeOptions
     */
    void setImeOptions(int imeOptions);

    /**
     * Set selection.
     *
     * @param index index
     */
    void setSelection(int index);

    /**
     * Remove text watcher.
     *
     * @param textWatcher text watcher
     */
    void removeTextChangeListener(TextWatcher textWatcher);

    /**
     * Set input filters.
     *
     * @param filters filters
     */
    void setFilters(InputFilter[] filters);

    /**
     * Get input length.
     *
     * @return length
     */
    int length();

    /**
     * Get text as string.
     *
     * @return text
     */
    String getTextAsString();

    /**
     * Get text.
     *
     * @return text
     */
    CharSequence getText();

    /**
     * Get edit text.
     *
     * @return edit text
     */
    LAMAEditText getEditText();
}
