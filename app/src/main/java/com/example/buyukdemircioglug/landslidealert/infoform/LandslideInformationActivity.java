package com.example.buyukdemircioglug.landslidealert.infoform;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.buyukdemircioglug.landslidealert.addphoto.AddPhotoFragment;
import com.example.buyukdemircioglug.landslidealert.location.BaseLocationActivity;
import com.example.buyukdemircioglug.landslidealert.util.GeocodeUtil;

public class LandslideInformationActivity extends BaseLocationActivity {

    // Camera activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    private LandslideInformationFragment containedFragment;

    public static Intent newIntent(Context context) {
        return new Intent(context, LandslideInformationActivity.class);
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

    /**
     * Launching camera app to capture image
     */
    public void captureImage() {
        final Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();

                if (extras != null) {
                    final Bitmap imageBitmap = (Bitmap) extras.get("data");

                    if (getCurrentFragment() != null && getCurrentFragment() instanceof AddPhotoFragment) {
                        ((AddPhotoFragment) getCurrentFragment()).setImage(imageBitmap);
                    }
                }
            }
        }
    }
}
