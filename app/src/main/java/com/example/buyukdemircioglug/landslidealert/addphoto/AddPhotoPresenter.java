package com.example.buyukdemircioglug.landslidealert.addphoto;

import android.content.Intent;
import android.provider.MediaStore;

import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.core.BasePresenter;
import com.example.buyukdemircioglug.landslidealert.core.navigation.ActivityNavigationBundle;
import com.example.buyukdemircioglug.landslidealert.util.ResourceRepository;

class AddPhotoPresenter extends BasePresenter<AddPhotoView> {

    void onAddPhotoButtonClicked() {
        ifViewAttached(AddPhotoView::showAddPhotoDialog);
    }

    void onSendButtonClicked() {
        ifViewAttached(AddPhotoView::sendEmail);
    }

    void choosePhotoFromGallerySelected() {
        final Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ifViewAttached(view -> view.handleNavigation(new ActivityNavigationBundle(
                galleryIntent,
                ResourceRepository.getInstance().getInteger(R.integer.request_code_select_image_from_gallery))
        ));
    }

    void takePhotoWithCameraSelected() {
        final Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        ifViewAttached(view -> view.handleNavigation(new ActivityNavigationBundle(
                cameraIntent,
                ResourceRepository.getInstance().getInteger(R.integer.request_code_open_camera))
        ));
    }

}

