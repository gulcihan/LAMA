package com.example.buyukdemircioglug.landslidealert.location;

import android.location.Location;
import android.location.LocationListener;

import com.google.android.gms.common.ConnectionResult;

public interface MyLocationListener extends LocationListener {

    void onShowLocationSettings();

    void onLocationSettingsFailed(Exception exception);

    void onConnectionFailed(ConnectionResult var1);

    void onLocationChanged(Location location);

    void onLocationNotFound();
}
