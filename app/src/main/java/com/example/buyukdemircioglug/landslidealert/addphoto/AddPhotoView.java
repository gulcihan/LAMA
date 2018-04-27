package com.example.buyukdemircioglug.landslidealert.addphoto;

import com.example.buyukdemircioglug.landslidealert.core.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface AddPhotoView extends BaseView {

    void displayIMEINumber();

    void showLocationData();

    void showCurrentTime(String today);

    void showAddPhotoDialog();

    void sendEmail();

}
