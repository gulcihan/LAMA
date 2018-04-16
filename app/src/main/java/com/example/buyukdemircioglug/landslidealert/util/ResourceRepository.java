package com.example.buyukdemircioglug.landslidealert.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import com.example.buyukdemircioglug.landslidealert.core.LAMAApp;

import java.util.Locale;

public class ResourceRepository {

    @SuppressLint("StaticFieldLeak")
    private static ResourceRepository resourceRepository;

    private final Context context;

    private ResourceRepository(Context context) {
        this.context = context;
    }

    public static ResourceRepository getInstance() {
        if (resourceRepository == null) {
            resourceRepository = new ResourceRepository(LAMAApp.getInstance().getApplicationContext());
        }
        return resourceRepository;
    }

    public String getString(@StringRes int resId) {
        return context.getString(resId);
    }

    public int getInteger(@IntegerRes int resId) {
        return context.getResources().getInteger(resId);
    }

    public int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(context, resId);
    }

    public Locale getDefaultLocale() {
        return context.getResources().getConfiguration().locale;
    }
}
