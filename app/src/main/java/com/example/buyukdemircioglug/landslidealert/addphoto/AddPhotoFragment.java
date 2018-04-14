package com.example.buyukdemircioglug.landslidealert.addphoto;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.core.BaseFragment;
import com.example.buyukdemircioglug.landslidealert.infoform.MainActivity;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import butterknife.BindView;
import butterknife.OnClick;

@FragmentWithArgs
public class AddPhotoFragment extends BaseFragment implements AddPhotoContract.View {

    @BindView(R.id.fragment_add_photo_image_view_one)
    ImageView imageViewOne;

    @BindView(R.id.fragment_add_photo_image_view_two)
    ImageView imageViewTwo;

    @BindView(R.id.fragment_add_photo_image_view_three)
    ImageView imageViewThree;

    @BindView(R.id.fragment_add_photo_image_view_four)
    ImageView imageViewFour;

    private AddPhotoContract.Presenter presenter;
    private ImageView selectedImageView;

    @Override
    protected int getResourceLayoutId() {
        return R.layout.fragment_add_photo;
    }

    @Override
    protected void initUserInterface(LayoutInflater inflater, View rootView) {

        setToolbarTitle(getString(R.string.add_photo_screen_title));
    }

    @Override
    public void setPresenter(AddPhotoContract.Presenter presenter) {
        this.presenter = presenter;
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

    /**
     * Launching camera app to capture image
     */
    @Override
    public void captureImage() {
        ((MainActivity) getActivity()).captureImage();
    }

    @Override
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
            presenter.onAddPhotoButtonClicked();
        }
    }

}



