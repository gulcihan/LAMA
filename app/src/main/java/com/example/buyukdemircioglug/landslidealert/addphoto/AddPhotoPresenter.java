package com.example.buyukdemircioglug.landslidealert.addphoto;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.core.navigation.ActivityNavigationBundle;
import com.example.buyukdemircioglug.landslidealert.util.ResourceRepository;

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
        view.showAddPhotoDialog();
    }

    @Override
    public void onSendButtonClicked() {
        view.sendEmail();
    }

    @Override
    public void choosePhotoFromGallerySelected() {
        final Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        view.handleNavigation(new ActivityNavigationBundle(
                galleryIntent,
                ResourceRepository.getInstance().getInteger(R.integer.request_code_select_image_from_gallery))
        );
    }

    @Override
    public void takePhotoWithCameraSelected() {
        final Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        view.handleNavigation(new ActivityNavigationBundle(
                cameraIntent,
                ResourceRepository.getInstance().getInteger(R.integer.request_code_open_camera))
        );
    }

}

