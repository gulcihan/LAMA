package com.example.buyukdemircioglug.landslidealert.userinput;

import android.support.annotation.NonNull;


public class UserInputPresenter implements UserInputContract.Presenter {


    private final UserInputContract.View userInputView;


    public UserInputPresenter(@NonNull UserInputContract.View userInputView) {
        this.userInputView = userInputView;
        this.userInputView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void editTask() {

    }
}
