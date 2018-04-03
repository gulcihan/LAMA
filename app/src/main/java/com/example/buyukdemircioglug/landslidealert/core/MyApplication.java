package com.example.buyukdemircioglug.landslidealert.core;

import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication instance;

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
    public static MyApplication getInstance() {
        return instance;
    }
}
