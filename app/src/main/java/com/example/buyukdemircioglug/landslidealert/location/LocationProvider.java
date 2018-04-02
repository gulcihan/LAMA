package com.example.buyukdemircioglug.landslidealert.location;

import android.annotation.TargetApi;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import static android.content.Context.LOCATION_SERVICE;

public class LocationProvider implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    // The minimum distance to change Updates in meters
    private static final long LOCATION_REFRESH_DISTANCE = 1; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long LOCATION_REFRESH_TIME = 1; // 1 minute

    private Context context;
    private final GoogleApiClient googleApiClient;
    private final MyLocationListener locationListener;
    private Location currentLocation;
    private LocationManager locationManager;

    public LocationProvider(Context context, MyLocationListener locationListener) {
        this.context = context;
        this.locationListener = locationListener;
        this.locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        this.googleApiClient = (new GoogleApiClient.Builder(context)).addApi(LocationServices.API)
                .addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        this.currentLocation = LocationServices.FusedLocationApi.getLastLocation(this.googleApiClient);

        if (this.currentLocation != null) {
            this.locationListener.onLocationChanged(this.currentLocation);
        } else {
            this.locationListener.onLocationNotFound();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public Location getCurrentLocation() {
        if (this.googleApiClient.isConnected()) {
            this.currentLocation = LocationServices.FusedLocationApi.getLastLocation(this.googleApiClient);
        }

        return this.currentLocation;
    }

    public void connectAndCheckLocationSettings() {
        this.googleApiClient.connect();
        this.checkLocationSettings();
    }

    public void checkLocationSettings() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(LocationRequest.create());
        final Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(context).checkLocationSettings(builder.build());

        result.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                //LocationProvider.this.locationListener.onShowLocationSettings();
            }
        });

        result.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                LocationProvider.this.locationListener.onLocationSettingsFailed(exception);
            }
        });
    }

    public void disconnect() {
        this.googleApiClient.disconnect();
    }

    public GoogleApiClient getGoogleApiClient() {
        return this.googleApiClient;
    }

    public boolean isLocationEnabled() {
        final int currentApiVersion = Build.VERSION.SDK_INT;

        if (currentApiVersion >= 19) {
            final int locationMode = this.getLocationMode();
            return locationMode == 2 || locationMode == 3 || locationMode == 1;
        } else {
            return this.isProviderEnabled("network") || this.isProviderEnabled("gps");
        }
    }

    /**
     * It is possible to get the device's current location mode since API level 19 (Kitkat):
     * 0 = LOCATION_MODE_OFF
     * 1 = LOCATION_MODE_SENSORS_ONLY
     * 2 = LOCATION_MODE_BATTERY_SAVING
     * 3 = LOCATION_MODE_HIGH_ACCURACY
     */
    @TargetApi(19)
    public int getLocationMode() {
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "location_mode");

        } catch (Settings.SettingNotFoundException var2) {
            Log.e(this.getClass().getSimpleName(), "Setting not found", var2);
            return -1;
        }
    }

    public void requestLocationUpdates(LocationRequest locationRequest) {

        if(this.googleApiClient.isConnected()) {

            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    LOCATION_REFRESH_TIME,
                    LOCATION_REFRESH_DISTANCE,
                    locationListener
            );

        } else {

            if(!this.googleApiClient.isConnecting()) {
                this.connectAndCheckLocationSettings();
            }
        }
    }


    public boolean isProviderEnabled(String provider) {
        return this.locationManager.isProviderEnabled(provider);
    }
}
