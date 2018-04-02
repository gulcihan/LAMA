/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package com.example.buyukdemircioglug.landslidealert.util;

import java.util.List;

public final class ListUtil {

    /**
     * Default constructor disabled.
     */
    private ListUtil() {
    }

    /**
     * Checks if given list is empty or null.
     *
     * @param list to be checked
     * @return true if the list is empty or list is null
     */
    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

}
