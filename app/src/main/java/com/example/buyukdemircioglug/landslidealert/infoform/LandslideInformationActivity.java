package com.example.buyukdemircioglug.landslidealert.infoform;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.location.BaseLocationActivity;
import com.example.buyukdemircioglug.landslidealert.util.GeocodeUtil;

public class LandslideInformationActivity extends BaseLocationActivity {

    private LandslideInformationFragment containedFragment;

    public static Intent newIntent(Context context) {
        return new Intent(context, LandslideInformationActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getToolBar().setTitle(getString(R.string.info_form_screen_title));
    }

    @Override
    protected void onLocationFound(Location location) {
        Log.e(getClass().getSimpleName(), "lat : " + location.getLatitude());
        Log.e(getClass().getSimpleName(), "long : " + location.getLongitude());

        final LandslideInformationFragment currentFragment = ((LandslideInformationFragment) getCurrentFragment());

        if (currentFragment!= null && currentFragment.isVisible()) {
            currentFragment.setLocationText(GeocodeUtil.createAddressText(this, location));
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
}