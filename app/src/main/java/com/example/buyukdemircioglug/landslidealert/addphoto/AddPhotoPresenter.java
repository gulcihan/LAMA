package com.example.buyukdemircioglug.landslidealert.addphoto;

import android.content.Intent;
import android.provider.MediaStore;

public class AddPhotoPresenter {

    public void onAddPhotoButtonClicked() {
        //view.showAddPhotoDialog();
    }

    public void onSendButtonClicked() {
        //view.sendEmail();
    }

    public void choosePhotoFromGallerySelected() {
        final Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        view.handleNavigation(new ActivityNavigationBundle(
//                galleryIntent,
//                ResourceRepository.getInstance().getInteger(R.integer.request_code_select_image_from_gallery))
//        );
    }

    public void takePhotoWithCameraSelected() {
        final Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        view.handleNavigation(new ActivityNavigationBundle(
//                cameraIntent,
//                ResourceRepository.getInstance().getInteger(R.integer.request_code_open_camera))
//        );
    }

}

