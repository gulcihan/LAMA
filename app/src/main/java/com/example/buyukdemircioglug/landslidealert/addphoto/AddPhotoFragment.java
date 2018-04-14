package com.example.buyukdemircioglug.landslidealert.addphoto;


import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
    ImageView imageViewLandslide;

    private AddPhotoContract.Presenter presenter;

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
    public void onAddPhotoButtonClicked() {
        // Checking camera availability
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getActivity(), "Sorry! Your device doesn't support camera", Toast.LENGTH_LONG).show();

        } else {
            presenter.onAddPhotoButtonClicked();
        }
    }

    /**
     * Launching camera app to capture image
     */
    @Override
    public void captureImage() {
        ((MainActivity) getActivity()).captureImage();
    }

    public void setImage(Bitmap imageBitmap) {
        if (imageBitmap != null) {
            imageViewLandslide.setImageBitmap(imageBitmap);
        }
    }

    /**
     * Checking device has camera hardware or not
     */
    private boolean isDeviceSupportCamera() {
        return getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

}



