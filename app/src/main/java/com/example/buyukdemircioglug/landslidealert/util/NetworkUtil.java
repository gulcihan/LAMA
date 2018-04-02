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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class NetworkUtil {

    /**
     * Default constructor disabled.
     */
    private NetworkUtil() {
    }

    /**
     * Convenience method for checking network connectivity.
     *
     * @param context Context
     * @return true if network connectivity exists or is in the process of being established, false otherwise.
     */

    public static boolean isConnectedOrConnecting(Context context) {
        final NetworkInfo activeNetwork = getNetworkInfo(context);

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Method for getting network info.
     *
     * @param context Context
     * @return a {@link NetworkInfo} object for the current default network
     *        or {@code null} if no network default network is currently active
     *
     */
    private static NetworkInfo getNetworkInfo(Context context) {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo();
    }

}
