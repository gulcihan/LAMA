package com.example.buyukdemircioglug.landslidealert.infoform;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.buyukdemircioglug.landslidealert.addphoto.AddPhotoContract;
import com.example.buyukdemircioglug.landslidealert.addphoto.AddPhotoFragmentBuilder;
import com.example.buyukdemircioglug.landslidealert.addphoto.AddPhotoPresenter;
import com.example.buyukdemircioglug.landslidealert.core.BaseFragment;
import com.example.buyukdemircioglug.landslidealert.core.navigation.FragmentNavigationBundle;
import com.example.buyukdemircioglug.landslidealert.util.DateTimeUtil;
import com.example.buyukdemircioglug.landslidealert.util.ResourceRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LandslideInformationPresenter implements LandslideInformationContract.Presenter {

    private final LandslideInformationContract.View view;

    public LandslideInformationPresenter(@NonNull LandslideInformationContract.View view) {
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
    public void onContinueButtonClicked(@NonNull LandslideInfo landslideInfo) {
        if (validateInputs(landslideInfo)) {
            final BaseFragment fragment = new AddPhotoFragmentBuilder().build();
            view.handleNavigation(new FragmentNavigationBundle(
                    fragment,
                    new AddPhotoPresenter((AddPhotoContract.View) fragment)
            ));
        }
    }

    private boolean validateInputs(@NonNull LandslideInfo info) {
        // Username Validation
        if (TextUtils.isEmpty(info.getUsername())) {
            view.showErrorForUserName();

        } else {
            view.setUserNameAsValid();
        }

        // Name Validation
        if (TextUtils.isEmpty(info.getName())) {
            view.showErrorForUserName();

        } else {
            view.setUserNameAsValid();
        }

        // Surname Validation
        if (TextUtils.isEmpty(info.getSurname())) {
            view.showErrorForSurname();

        } else {
            view.setSurnameAsValid();
        }

        return !TextUtils.isEmpty(info.getUsername())
                && !TextUtils.isEmpty(info.getName())
                && !TextUtils.isEmpty(info.getSurname());
    }

}

