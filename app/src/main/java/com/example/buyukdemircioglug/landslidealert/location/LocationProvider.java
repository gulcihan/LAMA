package com.example.buyukdemircioglug.landslidealert.location;

import android.annotation.SuppressLint;
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
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
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
    private FusedLocationProviderClient fusedLocationClient;

    public LocationProvider(Context context, MyLocationListener locationListener) {
        this.context = context;
        this.locationListener = locationListener;
        this.locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        this.googleApiClient = (new GoogleApiClient.Builder(context)).addApi(LocationServices.API)
                .addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        currentLocation = location;
                        // Got last known location. In some rare situations this can be null.

                        if (currentLocation != null) {
                            locationListener.onLocationChanged(currentLocation);
                        } else {
                            locationListener.onLocationNotFound();
                        }
                    }
                });
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void connectAndCheckLocationSettings() {
        this.googleApiClient.connect();
        this.checkLocationSettings();
    }

    public void checkLocationSettings() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(LocationRequest.create());
        final Task<LocationSettingsResponse> task = LocationServices.getSettingsClient(context).checkLocationSettings(builder.build());

        task.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(Task<LocationSettingsResponse> task) {
                try {
                    // All location settings are satisfied. The client can initialize location requests here.
                    LocationSettingsResponse response = task.getResult(ApiException.class);


                } catch (ApiException exception) {
                    switch (exception.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied. But could be fixed by showing the
                            // user a dialog.
                            ResolvableApiException resolvable = (ResolvableApiException) exception;
                            locationListener.onShowLocationSettings(resolvable);
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the dialog.
                            locationListener.onLocationSettingsFailed(exception);
                            break;

                        default:
                            break;
                    }
                }
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

    @SuppressLint("MissingPermission")
    public void requestLocationUpdates() {
        if (this.googleApiClient.isConnected()) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    LOCATION_REFRESH_TIME,
                    LOCATION_REFRESH_DISTANCE,
                    locationListener
            );
        } else {
            if (!this.googleApiClient.isConnecting()) {
                this.connectAndCheckLocationSettings();
            }
        }
    }

    public boolean isProviderEnabled(String provider) {
        return this.locationManager.isProviderEnabled(provider);
    }
}
