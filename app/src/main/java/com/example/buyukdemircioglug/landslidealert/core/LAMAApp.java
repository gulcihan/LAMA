package com.example.buyukdemircioglug.landslidealert.core;

import android.app.Application;

public class LAMAApp extends Application {

    private static LAMAApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * Application instance itself.
     *
     * @return the application that is running
     */
    public static LAMAApp getInstance() {
        return instance;
    }
}
