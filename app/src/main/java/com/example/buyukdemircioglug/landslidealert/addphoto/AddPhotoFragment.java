package com.example.buyukdemircioglug.landslidealert.addphoto;


import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.buyukdemircioglug.landslidealert.R;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import butterknife.BindView;
import butterknife.OnClick;

@FragmentWithArgs
public class AddPhotoFragment extends Fragment {

    @BindView(R.id.fragment_add_photo_image_view_one)
    ImageView imageViewOne;

    @BindView(R.id.fragment_add_photo_image_view_two)
    ImageView imageViewTwo;

    @BindView(R.id.fragment_add_photo_image_view_three)
    ImageView imageViewThree;

    @BindView(R.id.fragment_add_photo_image_view_four)
    ImageView imageViewFour;

    //private AddPhotoContract.Presenter presenter;
    private ImageView selectedImageView;

//    @Override
//    protected int getResourceLayoutId() {
//        return R.layout.fragment_add_photo;
//    }
//
//    @Override
//    protected void initUserInterface(LayoutInflater inflater, View rootView) {
//        setToolbarTitle(getString(R.string.add_photo_screen_title));
//    }

    @OnClick(R.id.fragment_add_photo_image_view_one)
    public void onAddPhotoOneButtonClicked() {
        selectedImageView = imageViewOne;
        addPhoto();
    }

    @OnClick(R.id.fragment_add_photo_image_view_two)
    public void onAddPhotoTwoButtonClicked() {
        selectedImageView = imageViewTwo;
        addPhoto();
    }

    @OnClick(R.id.fragment_add_photo_image_view_three)
    public void onAddPhotoThreeButtonClicked() {
        selectedImageView = imageViewThree;
        addPhoto();
    }

    @OnClick(R.id.fragment_add_photo_image_view_four)
    public void onAddPhotoFourButtonClicked() {
        selectedImageView = imageViewFour;
        addPhoto();
    }

    @OnClick(R.id.fragment_add_photo_button_send)
    public void onSendButtonClicked() {
        //presenter.onSendButtonClicked();
    }

    /**
     * Launching camera app to capture image
     */
    public void showAddPhotoDialog() {
        final AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Select Action");

        final String[] pictureDialogItems = {"Select photo from gallery", "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                //presenter.choosePhotoFromGallerySelected();
                                break;
                            case 1:
                                //presenter.takePhotoWithCameraSelected();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void sendEmail() {
        final String mailto = "mailto:bob@example.org" +
                "?cc=" + "alice@example.com" +
                "&subject=" + Uri.encode("asdasd") +
                "&body=" + Uri.encode(" wkehf wkjfkwehfkwf wkjfbwejkfb");

        final Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));

        try {
            startActivity(emailIntent);

        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity(), "Sorry! Your have no email sender app in your device", Toast.LENGTH_LONG).show();
        }
    }

    public void setImage(Bitmap imageBitmap) {
        if (imageBitmap != null) {
            selectedImageView.setImageBitmap(imageBitmap);
        }
    }

    /**
     * Checking device has camera hardware or not
     */
    private boolean isDeviceSupportCamera() {
        return getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    private void addPhoto() {
        // Checking camera availability
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getActivity(), "Sorry! Your device doesn't support camera", Toast.LENGTH_LONG).show();

        } else {
            //presenter.onAddPhotoButtonClicked();
        }
    }

}



