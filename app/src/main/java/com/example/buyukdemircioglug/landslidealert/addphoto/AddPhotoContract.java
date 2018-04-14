package com.example.buyukdemircioglug.landslidealert.addphoto;

import com.example.buyukdemircioglug.landslidealert.core.BasePresenter;
import com.example.buyukdemircioglug.landslidealert.core.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface AddPhotoContract {

    interface View extends BaseView<Presenter> {

        void captureImage();

        void sendEmail();

    }

    interface Presenter extends BasePresenter {

        void onAddPhotoButtonClicked();

        void onSendButtonClicked();

    }
}
