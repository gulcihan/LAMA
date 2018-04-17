package com.example.buyukdemircioglug.landslidealert.infoform;

import com.example.buyukdemircioglug.landslidealert.core.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface LandslideInformationView extends BaseView {

        void setDateInfo(String date);

        void setTimeInfo(String time);

        void showErrorForUserName();

        void setUserNameAsValid();

        void showErrorForName();

        void setNameAsValid();

        void showErrorForSurname();

        void setSurnameAsValid();

}
