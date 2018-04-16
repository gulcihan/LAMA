
package com.example.buyukdemircioglug.landslidealert.core;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.addphoto.AddPhotoFragment;
import com.example.buyukdemircioglug.landslidealert.core.navigation.ActivityNavigationBundle;
import com.example.buyukdemircioglug.landslidealert.core.navigation.FragmentNavigationBundle;
import com.example.buyukdemircioglug.landslidealert.core.navigation.NavigationBundle;
import com.hannesdorfmann.fragmentargs.FragmentArgs;

import java.io.IOException;

import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Extend all your fragments from this base fragment.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this); // read @Arg fields
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(getResourceLayoutId(), container, false);

        ButterKnife.bind(this, rootView);

        initUserInterface(inflater, rootView);

        return rootView;
    }


    public void handleNavigation(NavigationBundle navigationBundle) {
        if (navigationBundle instanceof ActivityNavigationBundle) {

            final ActivityNavigationBundle bundle = (ActivityNavigationBundle) navigationBundle;

            if (bundle.isRestartCurrentActivity()) {
                ((BaseActivity) getActivity()).restartActivity();

            } else if (bundle.isFinishCurrentActivity()) {
                getActivity().finish();

            } else if (bundle.isRequestCodeValid(bundle.getRequestCode())) {
                startActivityForResult(bundle.getIntent(), bundle.getRequestCode());
            }

        } else if (navigationBundle instanceof FragmentNavigationBundle) {
            ((BaseActivity) getActivity()).handleNavigation(navigationBundle);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.e("ASD", "lskdjlkdf");

        if (resultCode == RESULT_OK) {
            if (requestCode == getResources().getInteger(R.integer.request_code_select_image_from_gallery)) {
                if (data != null) {
                    final Uri contentURI = data.getData();
                    try {
                        final Fragment fragment = ((BaseActivity) getActivity()).getCurrentFragment();

                        if (fragment != null && fragment instanceof AddPhotoFragment) {
                            ((AddPhotoFragment) fragment).setImage(
                                    MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI));
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }

            } else if (requestCode == getResources().getInteger(R.integer.request_code_open_camera)) {
                final Bundle extras = data.getExtras();

                if (extras != null) {

                    final Fragment fragment = ((BaseActivity) getActivity()).getCurrentFragment();

                    if (fragment != null && fragment instanceof AddPhotoFragment) {
                        ((AddPhotoFragment) fragment).setImage((Bitmap) extras.get("data"));
                    }

                }
            }

        }


    }

    public String getFragmentTag() {
        return this.getClass().getSimpleName();
    }

    public void setToolbarTitle(String title) {
        ((BaseActivity) getActivity()).getToolBar().setTitle(title);
    }

    /**
     * Resource layout id of the fragment.
     * Child fragments must override this to have a layout.
     *
     * @return resource layout
     */
    protected abstract int getResourceLayoutId();

    /**
     * Initialize UI content elements.
     *
     * @param inflater layout inflater of fragment
     * @param rootView The fragment's root view
     */
    protected abstract void initUserInterface(LayoutInflater inflater, final View rootView);
}
