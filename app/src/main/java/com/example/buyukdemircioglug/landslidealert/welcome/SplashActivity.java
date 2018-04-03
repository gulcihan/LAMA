/*
 * Copyright 2003-2015 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package com.example.buyukdemircioglug.landslidealert.welcome;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;

import com.canelmas.let.AskPermission;
import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.core.BaseActivity;
import com.example.buyukdemircioglug.landslidealert.userinput.UserInputActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int splashDisplayLength = this.getResources().getInteger(R.integer.splash_display_length);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                navigateToUserInputScreen();

            }
        }, splashDisplayLength);
    }

    @Override
    protected int getContentResourceId() {
        return R.layout.activity_splash;
    }


    @Override
    protected Fragment getContainedFragment() {
        return null;
    }

    @Override
    protected void createPresenter() {
        // TODO ask Gunes
    }


    @AskPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    private void navigateToUserInputScreen() {
        SplashActivity.this.startActivity(UserInputActivity.newIntent(SplashActivity.this));
        SplashActivity.this.finish();
    }

}
