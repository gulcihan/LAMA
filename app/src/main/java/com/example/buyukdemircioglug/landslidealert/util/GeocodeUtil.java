/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package com.example.buyukdemircioglug.landslidealert.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

public final class GeocodeUtil {

    /**
     * Default constructor disabled.
     */
    private GeocodeUtil() {
    }

    public static String createAddressText(Context context, Location location) {
        final Address address = geocodeLocation(context, location);

        if (address == null) {
            return "";
        } else {
            return createAddressText(address);
        }
    }

    private static String createAddressText(@NonNull Address address) {
        return String.format("%s / %s / %s / %s",
                address.getSubLocality(),
                address.getSubAdminArea(),
                address.getAdminArea(),
                address.getCountryName()
        );
    }

    @Nullable
    private static Address geocodeLocation(Context context, Location location) {

        if (context == null || location == null || !Geocoder.isPresent()
                || !NetworkUtil.isConnectedOrConnecting(context)) {
            return null;
        }

        Address address = null;

        try {
            final Geocoder geocoder = new Geocoder(context);
            final List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (!ListUtil.isEmpty(addressList)) {
                address = addressList.get(0);
            }
        } catch (Exception e) {
            Log.e(GeocodeUtil.class.getSimpleName(), "Geocoding failed!", e);
        }

        return address;
    }
}
