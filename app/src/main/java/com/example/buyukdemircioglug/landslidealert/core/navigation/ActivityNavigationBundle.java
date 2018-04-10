package com.example.buyukdemircioglug.landslidealert.core.navigation;

import com.example.buyukdemircioglug.landslidealert.core.BaseActivity;

public class ActivityNavigationBundle extends NavigationBundle {

    private BaseActivity activity;
    private boolean restartCurrentActivity;
    private boolean finishCurrentActivity;

    public ActivityNavigationBundle(BaseActivity activity,
                                    boolean restartCurrentActivity,
                                    boolean finishCurrentActivity) {

        this.activity = activity;
        this.restartCurrentActivity = restartCurrentActivity;
        this.finishCurrentActivity = finishCurrentActivity;
    }

    public BaseActivity getActivity() {
        return activity;
    }

    public boolean isRestartCurrentActivity() {
        return restartCurrentActivity;
    }

    public boolean isFinishCurrentActivity() {
        return finishCurrentActivity;
    }
}