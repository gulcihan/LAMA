package com.example.buyukdemircioglug.landslidealert.infoform;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.buyukdemircioglug.landslidealert.addphoto.AddPhotoFragment;
import com.example.buyukdemircioglug.landslidealert.location.BaseLocationActivity;
import com.example.buyukdemircioglug.landslidealert.util.GeocodeUtil;

import java.io.IOException;

public class MainActivity extends BaseLocationActivity {

    // Camera activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int SELECT_FROM_GALLERY_IMAGE_REQUEST_CODE = 101;

    private LandslideInformationFragment containedFragment;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onLocationFound(Location location) {
        Log.e(getClass().getSimpleName(), "lat : " + location.getLatitude());
        Log.e(getClass().getSimpleName(), "long : " + location.getLongitude());

        if (getCurrentFragment() != null
                && getCurrentFragment() instanceof LandslideInformationFragment
                && getCurrentFragment().isVisible()) {
            ((LandslideInformationFragment) getCurrentFragment())
                    .setLocationText(GeocodeUtil.createAddressText(this, location));
        }
    }

    @Override
    protected void onLocationDataNotFound() {

    }

    @Override
    protected Fragment getContainedFragment() {
        containedFragment = new LandslideInformationFragmentBuilder().build();
        return containedFragment;
    }

    @Override
    protected void createPresenter() {
        new LandslideInformationPresenter(containedFragment);
    }

    @Override
    protected boolean hasToolbar() {
        return true;
    }

   @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_FROM_GALLERY_IMAGE_REQUEST_CODE) {
                if (data != null) {
                    final Uri contentURI = data.getData();
                    try {
                        final Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                        setImage(imageBitmap);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }

            } else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
                final Bundle extras = data.getExtras();

                if (extras != null) {
                    final Bitmap imageBitmap = (Bitmap) extras.get("data");
                    setImage(imageBitmap);
                }
            }
        }
    }

    public void showPictureDialog() {
        final AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");

        final String[] pictureDialogItems = {"Select photo from gallery", "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallery();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallery() {
        final Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, SELECT_FROM_GALLERY_IMAGE_REQUEST_CODE);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    private void setImage(Bitmap imageBitmap) {
        if (getCurrentFragment() != null && getCurrentFragment() instanceof AddPhotoFragment) {
            ((AddPhotoFragment) getCurrentFragment()).setImage(imageBitmap);
        }
    }
}
