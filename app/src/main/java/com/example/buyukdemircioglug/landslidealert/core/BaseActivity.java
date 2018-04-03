package com.example.buyukdemircioglug.landslidealert.core;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.canelmas.let.DeniedPermission;
import com.canelmas.let.Let;
import com.canelmas.let.RuntimePermissionListener;
import com.canelmas.let.RuntimePermissionRequest;
import com.example.buyukdemircioglug.landslidealert.R;

import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements RuntimePermissionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            final Fragment fragment = getContainedFragment();

            if (fragment != null) {
                addFragmentToActivity(getSupportFragmentManager(), fragment, getBaseFrameLayoutId());
            }
        }

        createPresenter();

        ButterKnife.bind(this);

        setContentView(getContentResourceId());

        final Toolbar toolbar = findViewById(R.id.toolbar);

        if (hasToolbar()) {
            setSupportActionBar(toolbar);

        } else if (toolbar != null) {
            toolbar.setVisibility(View.GONE);
        }
    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     *
     */
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment,
                                              int frameId) {

        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    /**
     * Get current visible fragment.
     *
     * @return fragment which is visible currently
     */
    protected Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(getBaseFrameLayoutId());
    }

    public ActionBar getToolBar() {
        return getSupportActionBar();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Let.handle(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onShowPermissionRationale(List<String> permissionList, RuntimePermissionRequest permissionRequest) {
        permissionRequest.retry();
    }

    @Override
    public void onPermissionDenied(List<DeniedPermission> deniedPermissionList) {
        // Do nothing
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
     * Get whether activity has a toolbar.
     *
     * @return true if activity has a toolbar, false otherwise
     */
    protected boolean hasToolbar() {
        return false;
    }

    /**
     * Get initial fragment instance.
     *
     * @return Fragment.
     */
    protected abstract Fragment getContainedFragment();

    /**
     * Get related presenter instance of the added fragment.
     *
     * @return Fragment.
     */
    protected abstract void createPresenter();

}
