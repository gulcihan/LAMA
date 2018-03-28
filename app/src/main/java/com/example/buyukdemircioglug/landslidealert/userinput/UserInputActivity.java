package com.example.buyukdemircioglug.landslidealert.userinput;

import android.support.v4.app.Fragment;

import com.example.buyukdemircioglug.landslidealert.core.BaseActivity;

public class UserInputActivity extends BaseActivity {

    @Override
    protected Fragment getContainedFragment() {
        return new UserInputFragmentBuilder().build();
    }

    @Override
    protected boolean hasToolbar() {
        return true;
    }
}
