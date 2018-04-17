package com.example.buyukdemircioglug.landslidealert.infoform;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.buyukdemircioglug.landslidealert.core.BaseActivity;

public class MainActivity extends BaseActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected Fragment getContainedFragment() {
        return new LandslideInformationFragmentBuilder().build();
    }

    @Override
    protected boolean hasToolbar() {
        return true;
    }

}
