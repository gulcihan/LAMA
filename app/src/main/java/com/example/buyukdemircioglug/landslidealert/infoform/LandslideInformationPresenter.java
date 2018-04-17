package com.example.buyukdemircioglug.landslidealert.infoform;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.buyukdemircioglug.landslidealert.core.BasePresenter;
import com.example.buyukdemircioglug.landslidealert.util.DateTimeUtil;
import com.example.buyukdemircioglug.landslidealert.util.ResourceRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LandslideInformationPresenter extends BasePresenter<LandslideInformationView> {

    public void onContinueButtonClicked(@NonNull LandslideInfo landslideInfo) {
        if (validateInputs(landslideInfo)) {

//            ifViewAttached(view -> view.handleNavigation(
//                    new FragmentNavigationBundle(new AddPhotoFragmentBuilder().build(), this)
//            ));
        }
    }

    private void showDateInfo() {
        final Date date = Calendar.getInstance().getTime();
        final Locale locale = ResourceRepository.getInstance().getDefaultLocale();

        // Display a date in day, month, year format
        final DateFormat formatter = new SimpleDateFormat(DateTimeUtil.DEFAULT_DATE_PATTERN, locale);
        final String today = formatter.format(date);

        ifViewAttached(view -> view.setDateInfo(today));

    }

    private void showTimeInfo() {
        final Date date = Calendar.getInstance().getTime();
        final Locale locale = ResourceRepository.getInstance().getDefaultLocale();

        // Display a time in hour and minute format
        final DateFormat formatter = new SimpleDateFormat(DateTimeUtil.DEFAULT_TIME_PATTERN, locale);
        final String time = formatter.format(date);

        ifViewAttached(view -> view.setTimeInfo(time));
    }

    private boolean validateInputs(@NonNull LandslideInfo info) {
        // Username Validation
        if (TextUtils.isEmpty(info.getUsername())) {
            ifViewAttached(LandslideInformationView::showErrorForUserName);

        } else {
            ifViewAttached(LandslideInformationView::setUserNameAsValid);
        }

        // Name Validation
        if (TextUtils.isEmpty(info.getName())) {
            ifViewAttached(LandslideInformationView::showErrorForName);

        } else {
            ifViewAttached(LandslideInformationView::setNameAsValid);
        }

        // Surname Validation
        if (TextUtils.isEmpty(info.getSurname())) {
            ifViewAttached(LandslideInformationView::showErrorForSurname);

        } else {
            ifViewAttached(LandslideInformationView::setSurnameAsValid);
        }

        return !TextUtils.isEmpty(info.getUsername())
                && !TextUtils.isEmpty(info.getName())
                && !TextUtils.isEmpty(info.getSurname());
    }

}

