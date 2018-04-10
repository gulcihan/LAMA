package com.example.buyukdemircioglug.landslidealert.userinput;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.core.BaseFragment;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

@FragmentWithArgs
public class ImageFragment extends BaseFragment {

    // Camera activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    @BindView(R.id.fragment_image_image_view)
    ImageView imageViewLandslide;

    @Override
    protected int getResourceLayoutId() {
        return R.layout.fragment_image;
    }

    @Override
    protected void initUserInterface(LayoutInflater inflater, View rootView) {

    }

    @OnClick(R.id.fragment_image_image_view)
    public void onImageViewLandslideClicked() {

        // Checking camera availability
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getActivity(), "Sorry! Your device doesn't support camera", Toast.LENGTH_LONG).show();

        } else {
            captureImage();
        }

    }

    /**
     * Checking device has camera hardware or not
     */
    private boolean isDeviceSupportCamera() {
        return getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    /**
     * Launching camera app to capture image
     */
    private void captureImage() {
        final Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();

            if (extras != null) {
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageViewLandslide.setImageBitmap(imageBitmap);
            }
        }
    }

}



