package com.example.buyukdemircioglug.landslidealert.addphoto;


import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.canelmas.let.AskPermission;
import com.canelmas.let.DeniedPermission;
import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.core.BaseFragment;
import com.example.buyukdemircioglug.landslidealert.infoform.LandslideInfo;
import com.example.buyukdemircioglug.landslidealert.util.DeviceUtil;
import com.example.buyukdemircioglug.landslidealert.util.ListUtil;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

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

    @BindView(R.id.fragment_add_photo_text_view_imei)
    TextView textViewImei;

    @BindView(R.id.fragment_add_photo_text_view_today)
    TextView textViewToday;

    @BindView(R.id.fragment_add_photo_text_view_location)
    TextView textViewLocation;

    @Arg
    LandslideInfo landslideInfo;

    private ImageView selectedImageView;

    private ArrayList<Bitmap> imageList = new ArrayList<>();

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
        presenter.start();
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
    @AskPermission(Manifest.permission.READ_PHONE_STATE)
    public void displayIMEINumber() {
        textViewImei.setText(getString(R.string.general_imei, DeviceUtil.getIMEINumber(getActivity())));
    }

    @Override
    public void showLocationData() {
        // TODO location text will be set here.
        //textViewLocation.setText("");
    }

    @Override
    public void showCurrentTime(String today) {
        textViewToday.setText(today);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == getResources().getInteger(R.integer.request_code_select_image_from_gallery)) {
                if (data != null) {
                    final Uri contentURI = data.getData();
                    try {
                        setImage(MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI));

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            } else if (requestCode == getResources().getInteger(R.integer.request_code_open_camera)) {
                final Bundle extras = data.getExtras();
                if (extras != null) {
                    setImage((Bitmap) extras.get("data"));
                }
            }
        }
    }

    @AskPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    @Override
    public void sendEmail() {
        final String mailto = "mailto:bob@example.org" +
                "?cc=" + "alice@example.com" +
                "&subject=" + Uri.encode("!!! Landslide Alert !!!") +
                "&body=" + Uri.encode(getEmailBody());

        final Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));

        if (!ListUtil.isEmpty(imageList)) {
            for (Bitmap bitmap : imageList) {

                Log.e("ASD", bitmap.toString());

                final String pathOfBitmap = MediaStore.Images.Media.insertImage(
                        getActivity().getContentResolver(),
                        bitmap,
                        "title",
                        null
                );

                final Uri bmpUri = Uri.parse(pathOfBitmap);
                emailIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            }
        }

        try {
            startActivity(emailIntent);

        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity(), "Sorry! Your have no email sender app in your device", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPermissionDenied(List<DeniedPermission> deniedPermissionList) {
        Snackbar.make(imageViewFour, "You didn't grant a permission :/", Snackbar
                .LENGTH_LONG).show();
    }

    private String getEmailBody() {
        return String.format("Username : %s\n" +
                        "Name : %s\n" +
                        "Surname : %s\n" +
                        "Event Date : %s\n" +
                        "Event Time : %s\n" +
                        "Event Location : %s\n" +
                        "Any injury of death : %s\n" +
                        "Damage Description : %s\n" +
                        "Other Observations : %s",
                landslideInfo.getUsername(),
                landslideInfo.getName(),
                landslideInfo.getSurname(),
                landslideInfo.getEventDate(),
                landslideInfo.getEventTime(),
                landslideInfo.getEventLocation(),
                landslideInfo.isAnyInjuryOrDeath(),
                landslideInfo.getDamageDescription(),
                landslideInfo.getOtherObservations()
        );
    }

    private void setImage(Bitmap imageBitmap) {
        if (imageBitmap != null) {
            imageList.add(imageBitmap);
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



