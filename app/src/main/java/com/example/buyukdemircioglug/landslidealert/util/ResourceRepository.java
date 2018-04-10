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

    public void getString(@StringRes int resId) {
        context.getString(resId);
    }

    public void getInteger(@IntegerRes int resId) {
        context.getResources().getInteger(resId);
    }

    public void getColor(@ColorRes int resId) {
        ContextCompat.getColor(context, resId);
    }

    public Locale getDefaultLocale() {
        return context.getResources().getConfiguration().locale;
    }
}
