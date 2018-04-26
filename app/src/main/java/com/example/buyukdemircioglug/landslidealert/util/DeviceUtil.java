/*
 * Copyright 2003-2017 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */
package com.example.buyukdemircioglug.landslidealert.util;

import android.Manifest;
import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;

public final class DeviceUtil {

    /**
     * Private constructor for util class.
     */
    private DeviceUtil() {
    }

    /**
     * IMEI of phone.
     * <p/>
     * Warning : This works only on phones.
     *
     * @param context app context.
     * @return imei number if exists.
     */
    public static String getIMEINumber(Context context) {
        final TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        return telephonyManager.getDeviceId();
    }

}
