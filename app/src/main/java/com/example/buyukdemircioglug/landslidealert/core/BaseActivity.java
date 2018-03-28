package com.example.buyukdemircioglug.landslidealert.core;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.buyukdemircioglug.landslidealert.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        setContentView(getContentResourceId());

        final Toolbar toolbar = findViewById(R.id.toolbar);

        if (hasToolbar()) {
            setSupportActionBar(toolbar);

        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

    /**
     * Method to get activity's UI content frame layout resource id.
     *
     * @return The activiti's frame layout resource id
     */
    protected int getBaseFrameLayoutId() {
        return R.id.act_base_frag_container;
    }

    /**
     * Method to get activity's UI content layout resource id.
     *
     * @return The activity's content's resource id
     */
    protected int getContentResourceId() {
        return R.layout.activity_base;
    }

    /**
     * Get initial fragment instance.
     *
     * @return Fragment.
     */
    protected abstract Fragment getContainedFragment();

    /**
     * Get whether activity has a toolbar.
     *
     * @return true if activity has a toolbar, false otherwise
     */
    protected boolean hasToolbar() {
        return false;
    }

}
