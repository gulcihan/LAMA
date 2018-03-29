package com.example.buyukdemircioglug.landslidealert.userinput;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.core.BaseActivity;

public class UserInputActivity extends BaseActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, UserInputActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getToolBar().setTitle(getString(R.string.user_input_screen_title));
    }

    @Override
    protected Fragment getContainedFragment() {
        return new UserInputFragmentBuilder().build();
    }

    @Override
    protected boolean hasToolbar() {
        return true;
    }
}
