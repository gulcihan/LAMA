package com.example.buyukdemircioglug.landslidealert.location;

import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.example.buyukdemircioglug.landslidealert.core.BaseActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ResolvableApiException;

public abstract class BaseLocationActivity extends BaseActivity implements MyLocationListener {

    private static final int LOCATION_SERVICES_REQUEST_CODE = 1;

    private LocationProvider locationProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationProvider = new LocationProvider(this, this);
    }

    // Call connect() in onStart()
    @Override
    protected void onStart() {
        super.onStart();
        locationProvider.connectAndCheckLocationSettings();
    }

    // Call disconnect() in onStop(), regardless of the state.
    @Override
    protected void onStop() {
        // Google api client is disconnected here
        locationProvider.disconnect();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        locationProvider = null;
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == LOCATION_SERVICES_REQUEST_CODE) {
            requestLocation();

        } else if (resultCode == RESULT_CANCELED) {
            onLocationDataNotFound();
        }
    }

    @Override
    public void onShowLocationSettings(ResolvableApiException resolvable) {
        // Location settings are not satisfied. But could be fixed by showing the user a dialog.
        showNativeLocationDialog(resolvable);
    }

    @Override
    public void onLocationSettingsFailed(Exception exception) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult var1) {
        onLocationDataNotFound();
    }

    @Override
    public void onLocationChanged(Location location) {
        //onConnected and current location found
        if (location != null) {
            onLocationFound(location);
        } else {
            onLocationDataNotFound();
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onLocationNotFound() {

    }


    /**
     * Callback method when location is found.
     *
     * @param location location
     */
    protected abstract void onLocationFound(Location location);

    /**
     * Callback method when location data is not found.
     */
    protected abstract void onLocationDataNotFound();

    /**
     * Show native location dialog (that asks user permission for enabling location).
     */
    private void showNativeLocationDialog(ResolvableApiException resolvable) {
        try {
            // Show the dialog by calling startResolutionForResult(),
            // and check the result in onActivityResult().
            resolvable.startResolutionForResult(this, LOCATION_SERVICES_REQUEST_CODE);

        } catch (IntentSender.SendIntentException e) {
            Log.e(getClass().getSimpleName(), "Send Intent Exception", e);
        }
    }


    /**
     * Request location.
     */
    private void requestLocation() {
        if (locationProvider.getGoogleApiClient().isConnecting()) {
            return;
        }

        if (locationProvider.getGoogleApiClient().isConnected() && !locationProvider.isLocationEnabled()) {
            locationProvider.checkLocationSettings();
            return;
        }

        locationProvider.requestLocationUpdates();
    }
}
