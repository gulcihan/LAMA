package com.example.buyukdemircioglug.landslidealert.infoform;

import android.support.annotation.NonNull;

import com.example.buyukdemircioglug.landslidealert.core.BasePresenter;
import com.example.buyukdemircioglug.landslidealert.core.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface LandslideInformationContract {

    interface View extends BaseView<Presenter> {

        void setDateInfo(String date);

        void setTimeInfo(String time);

        void setLocationInfo(String location);

        void showErrorForUserName();

        void setUserNameAsValid();

        void showErrorForName();

        void setNameAsValid();

        void showErrorForSurname();

        void setSurnameAsValid();

    }

    interface Presenter extends BasePresenter {

        void onContinueButtonClicked(@NonNull LandslideInfo info);

    }
}
