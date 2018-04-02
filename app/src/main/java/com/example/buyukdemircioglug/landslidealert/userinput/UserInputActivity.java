package com.example.buyukdemircioglug.landslidealert.userinput;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.location.BaseLocationActivity;
import com.example.buyukdemircioglug.landslidealert.util.GeocodeUtil;

public class UserInputActivity extends BaseLocationActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, UserInputActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getToolBar().setTitle(getString(R.string.user_input_screen_title));
    }

    @Override
    protected void onLocationFound(Location location) {
        Log.e(getClass().getSimpleName(), "lat : " + location.getLatitude());
        Log.e(getClass().getSimpleName(), "long : " + location.getLongitude());

        ((UserInputFragment) getCurrentFragment()).setLocationText(GeocodeUtil.createAddressText(this, location));
    }

    @Override
    protected void onLocationDataNotFound() {

    }

    @Override
    protected Fragment getContainedFragment() {
        return new UserInputFragmentBuilder().build();
    }

    @Override
    protected boolean hasToolbar() {
        return true;
    }
}
