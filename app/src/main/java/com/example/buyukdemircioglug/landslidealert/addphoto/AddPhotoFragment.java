package com.example.buyukdemircioglug.landslidealert.addphoto;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.core.BaseFragment;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import butterknife.BindView;
import butterknife.OnClick;

@FragmentWithArgs
public class AddPhotoFragment
        extends BaseFragment<AddPhotoView, AddPhotoPresenter, AddPhotoViewState>
        implements AddPhotoView {

    @BindView(R.id.fragment_add_photo_image_view_one)
    ImageView imageViewOne;

    @BindView(R.id.fragment_add_photo_image_view_two)
    ImageView imageViewTwo;

    @BindView(R.id.fragment_add_photo_image_view_three)
    ImageView imageViewThree;

    @BindView(R.id.fragment_add_photo_image_view_four)
    ImageView imageViewFour;

    private ImageView selectedImageView;

    @Override
    protected int getResourceLayoutId() {
        return R.layout.fragment_add_photo;
    }

    @Override
    protected void initUserInterface(LayoutInflater inflater, View rootView) {
        setToolbarTitle(getString(R.string.add_photo_screen_title));
    }

    @NonNull
    @Override
    public AddPhotoPresenter createPresenter() {
        return new AddPhotoPresenter();
    }

    @NonNull
    @Override
    public AddPhotoViewState createViewState() {
        return new AddPhotoViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        // No operation needed.
    }

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
        presenter.onSendButtonClicked();
    }

    @Override
    public void showAddPhotoDialog() {
        final AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Select Action");

        final String[] pictureDialogItems = {"Select photo from gallery", "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                (dialog, which) -> {
                    switch (which) {
                        case 0:
                            presenter.choosePhotoFromGallerySelected();
                            break;
                        case 1:
                            presenter.takePhotoWithCameraSelected();
                            break;
                    }
                });
        pictureDialog.show();
    }

    @Override
    public void sendEmail() {
        final String mailto = "mailto:bob@example.org" +
                "?cc=" + "alice@example.com" +
                "&subject=" + Uri.encode("!!! Landslide Alert !!!") +
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

    private boolean isDeviceSupportCamera() {
        return getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    private void addPhoto() {
        // Checking camera availability
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getActivity(), "Sorry! Your device doesn't support camera", Toast.LENGTH_LONG).show();

        } else {
            presenter.onAddPhotoButtonClicked();
        }
    }

}



