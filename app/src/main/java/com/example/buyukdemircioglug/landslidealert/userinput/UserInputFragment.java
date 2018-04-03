package com.example.buyukdemircioglug.landslidealert.userinput;

import android.view.LayoutInflater;
import android.view.View;

import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.core.BaseFragment;
import com.example.buyukdemircioglug.landslidealert.core.BasePresenter;
import com.example.buyukdemircioglug.landslidealert.view.CustomButton;
import com.example.buyukdemircioglug.landslidealert.view.CustomTextView;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

@FragmentWithArgs
public class UserInputFragment extends BaseFragment {

    @BindView(R.id.fragment_user_input_text_view_date)
    CustomTextView textViewDate;

    @BindView(R.id.fragment_user_input_text_view_time)
    CustomTextView textViewTime;

    @BindView(R.id.fragment_user_input_text_view_location)
    CustomTextView textViewLocation;

    @BindView(R.id.fragment_user_input_button_continue)
    CustomButton buttonContinue;

    @Override
    protected int getResourceLayoutId() {
        return R.layout.fragment_user_input;
    }

    @Override
    protected void initUserInterface(LayoutInflater inflater, View rootView) {

        final Date date = Calendar.getInstance().getTime();

        final Locale locale = getResources().getConfiguration().locale;

        //
        // Display a date in day, month, year format
        //
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", locale);
        String today = formatter.format(date);
        textViewDate.setText(today);

        DateFormat formatter2 = new SimpleDateFormat("HH:mm", locale);
        String time = formatter2.format(date);
        textViewTime.setText(time);

    }

    @Override
    public void setPresenter(BasePresenter presenter) {

    }

    public void setLocationText (String locationText) {
        textViewLocation.setText(getString(R.string.input_location, locationText));
    }

    @OnClick(R.id.fragment_user_input_button_continue)
    public void onButtonContinueClicked() {

    }
}
