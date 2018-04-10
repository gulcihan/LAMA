package com.example.buyukdemircioglug.landslidealert.userinput;

import android.support.annotation.NonNull;

import com.example.buyukdemircioglug.landslidealert.core.navigation.FragmentNavigationBundle;
import com.example.buyukdemircioglug.landslidealert.util.DateTimeUtil;
import com.example.buyukdemircioglug.landslidealert.util.ResourceRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UserInputPresenter implements UserInputContract.Presenter {

    private final UserInputContract.View view;

    public UserInputPresenter(@NonNull UserInputContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        showDateInfo();
        showTimeInfo();
        showLocation();
    }

    @Override
    public void stop() {

    }

    private void showDateInfo() {
        final Date date = Calendar.getInstance().getTime();
        final Locale locale = ResourceRepository.getInstance().getDefaultLocale();

        // Display a date in day, month, year format
        final DateFormat formatter = new SimpleDateFormat(DateTimeUtil.DEFAULT_DATE_PATTERN, locale);
        final String today = formatter.format(date);
        view.setDateInfo(today);
    }

    private void showTimeInfo() {
        final Date date = Calendar.getInstance().getTime();
        final Locale locale = ResourceRepository.getInstance().getDefaultLocale();

        // Display a time in hour and minute format
        final DateFormat formatter = new SimpleDateFormat(DateTimeUtil.DEFAULT_TIME_PATTERN, locale);
        final String time = formatter.format(date);
        view.setTimeInfo(time);
    }

    private void showLocation() {
        // TODO location will be handled.
    }

    @Override
    public void onContinueButtonClicked() {
        view.handleNavigation(new FragmentNavigationBundle(new ImageFragmentBuilder().build()));
    }
}

