package com.example.buyukdemircioglug.landslidealert.core.navigation;

import android.content.Intent;

import com.example.buyukdemircioglug.landslidealert.core.BaseActivity;

public class ActivityNavigationBundle extends NavigationBundle {

    private BaseActivity activity;
    private boolean restartCurrentActivity;
    private boolean finishCurrentActivity;
    private Intent intent;
    private int requestCode = Integer.MIN_VALUE;

    public ActivityNavigationBundle(BaseActivity activity,
                                    boolean restartCurrentActivity,
                                    boolean finishCurrentActivity) {

        this.activity = activity;
        this.restartCurrentActivity = restartCurrentActivity;
        this.finishCurrentActivity = finishCurrentActivity;
    }

    public ActivityNavigationBundle(Intent intent, int requestCode) {
        this.intent = intent;
        this.requestCode = requestCode;
    }

    public BaseActivity getActivity() {
        return activity;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public Intent getIntent() {
        return intent;
    }

    public boolean isRestartCurrentActivity() {
        return restartCurrentActivity;
    }

    public boolean isFinishCurrentActivity() {
        return finishCurrentActivity;
    }

    public boolean isRequestCodeValid(int requestCode) {
        return requestCode != Integer.MIN_VALUE;
    }
}