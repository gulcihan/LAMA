package com.example.buyukdemircioglug.landslidealert.infoform;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.buyukdemircioglug.landslidealert.location.BaseLocationActivity;
import com.example.buyukdemircioglug.landslidealert.util.GeocodeUtil;

public class MainActivity extends BaseLocationActivity {

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

}
