package com.example.buyukdemircioglug.landslidealert.addphoto;

import android.support.annotation.NonNull;

public class AddPhotoPresenter implements AddPhotoContract.Presenter {

    private final AddPhotoContract.View view;

    public AddPhotoPresenter(@NonNull AddPhotoContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void onAddPhotoButtonClicked() {
        view.captureImage();
    }

    @Override
    public void onSendButtonClicked() {
        view.sendEmail();
    }
}

