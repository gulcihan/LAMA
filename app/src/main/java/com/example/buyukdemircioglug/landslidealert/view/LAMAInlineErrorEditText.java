package com.example.buyukdemircioglug.landslidealert.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.util.KeyboardUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LAMAInlineErrorEditText extends LinearLayout implements LAMAInlineErrorView {

    private static final int INVALID = -1;

    @BindView(R.id.layout_inline_error_edit_text_input)
    LAMAEditText editText;

    @BindView(R.id.layout_inline_error_text_view_error_message)
    LAMATextView textViewError;


    public LAMAInlineErrorEditText(Context context) {
        this(context, null);
    }

    public LAMAInlineErrorEditText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LAMAInlineErrorEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    /**
     * Inits the EditText.
     *
     * @param attrs attributes
     */
    private void init(AttributeSet attrs) {

        setOrientation(VERTICAL);

        final TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LAMAInlineErrorEditText);

        inflate(getContext(), R.layout.layout_inline_error_edit_text, this);

        if (isInEditMode() || attrs == null) {
            return;
        }

        ButterKnife.bind(this);

        editText.setHint(typedArray.getString(R.styleable.LAMAInlineErrorEditText_android_hint));

        final int maxLength = typedArray.getInt(R.styleable.LAMAInlineErrorEditText_android_maxLength, INVALID);
        if (maxLength != INVALID) {
            editText.addInputFilters(new InputFilter.LengthFilter(maxLength));
        }

        final int maxLines = typedArray.getInt(R.styleable.LAMAInlineErrorEditText_android_maxLines, INVALID);
        if (maxLines != INVALID) {
            editText.setMaxLines(maxLines);
        }


        final int inputType = typedArray.getInt(R.styleable.LAMAInlineErrorEditText_android_inputType, INVALID);
        if (inputType != INVALID) {
            editText.setInputType(inputType);
        }

        final int ems = typedArray.getInt(R.styleable.LAMAInlineErrorEditText_android_ems, INVALID);
        if (ems != INVALID) {
            editText.setEms(ems);
        }

        final float textSize = typedArray.getDimensionPixelSize(R.styleable.LAMAInlineErrorEditText_android_textSize, INVALID);
        if (textSize != INVALID) {
            editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        final int gravity = typedArray.getInt(R.styleable.LAMAInlineErrorEditText_android_gravity, INVALID);
        if (gravity != INVALID) {
            editText.setGravity(gravity);
        }

        final int imeOptions = typedArray.getInt(R.styleable.LAMAInlineErrorEditText_android_imeOptions, INVALID);
        if (imeOptions != INVALID) {
            setImeOptions(imeOptions);
        }

        final boolean enabled = typedArray.getBoolean(R.styleable.LAMAInlineErrorEditText_android_enabled, true);
        editText.setEnabled(enabled);

        final boolean singleLine = typedArray.getBoolean(R.styleable.LAMAInlineErrorEditText_android_singleLine, false);
        editText.setSingleLine(singleLine);

        final String errorText = typedArray.getString(R.styleable.LAMAInlineErrorEditText_errorText);
        textViewError.setText(errorText);

        final int errorState = typedArray.getInt(R.styleable.LAMAInlineErrorEditText_errorState, LAMAInlineErrorView.HIDDEN);
        setErrorState(errorState == LAMAInlineErrorView.VISIBLE);

        final Drawable background = typedArray.getDrawable(R.styleable.LAMAInlineErrorEditText_android_background);
        if (background != null) {
            editText.setBackground(background);
        }

        final int textColor = typedArray.getColor(R.styleable.LAMAInlineErrorEditText_android_textColor, INVALID);
        if (textColor != INVALID) {
            editText.setTextColor(textColor);
        }

        typedArray.recycle();
    }

    @Override
    public void setHint(String text) {
        editText.setHint(text);
    }

    @Override
    public void setText(String text) {
        editText.setText(text);
        setSelection(length());
    }

    @Override
    public void setErrorText(String text) {
        textViewError.setText(text);
    }

    @Override
    public void setErrorState(boolean errored) {
        if (errored) {
            textViewError.setVisibility(View.VISIBLE);
            KeyboardUtil.hideKeyboard(this.editText);

        } else {
            textViewError.setVisibility(View.GONE);
        }
    }

    @Override
    public void setInputType(int type) {
        editText.setInputType(type);
    }

    @Override
    public void addTextChangedListener(TextWatcher textWatcher) {
        editText.addTextChangedListener(textWatcher);
    }

    @Override
    public void setOnEditorActionListener(TextView.OnEditorActionListener listener) {
        editText.setOnEditorActionListener(listener);
    }

    @Override
    public void setImeOptions(int imeOptions) {
        editText.setImeOptions(imeOptions);
    }

    @Override
    public void setSelection(int index) {
        editText.setSelection(index);
    }

    @Override
    public void removeTextChangeListener(TextWatcher textWatcher) {
        editText.removeTextChangedListener(textWatcher);
    }

    @Override
    public void setFilters(InputFilter[] filters) {
        editText.setFilters(filters);
    }

    @Override
    public int length() {
        return editText.length();
    }

    @Override
    public String getTextAsString() {
        return getText().toString();
    }

    @Override
    public CharSequence getText() {
        return editText.getText();
    }

    @Override
    public LAMAEditText getEditText() {
        return editText;
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(editText.getText());
    }

    public boolean isEnabled() {
        return editText.isEnabled();
    }

    public void setEnabled(boolean enabled) {
        editText.setEnabled(enabled);
    }

    public void setSingleLine(boolean singleLine) {
        editText.setSingleLine(singleLine);
    }

    public void clear() {
        editText.setText("");
    }
}
