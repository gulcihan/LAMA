package com.example.buyukdemircioglug.landslidealert.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.canelmas.let.DeniedPermission;
import com.canelmas.let.Let;
import com.canelmas.let.RuntimePermissionListener;
import com.canelmas.let.RuntimePermissionRequest;
import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.core.navigation.ActivityNavigationBundle;
import com.example.buyukdemircioglug.landslidealert.core.navigation.FragmentNavigationBundle;
import com.example.buyukdemircioglug.landslidealert.core.navigation.NavigationBundle;

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
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
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

    public void handleNavigation(NavigationBundle navigationBundle) {
        if (navigationBundle instanceof ActivityNavigationBundle) {

            final ActivityNavigationBundle bundle = (ActivityNavigationBundle) navigationBundle;

            if (bundle.isRestartCurrentActivity()) {
                restartActivity();

            } else if (bundle.isFinishCurrentActivity()) {
                finish();
            }
            else if (bundle.isRequestCodeValid(bundle.getRequestCode())) {
                startActivityForResult(bundle.getIntent(), bundle.getRequestCode());
            }

        } else if (navigationBundle instanceof FragmentNavigationBundle) {
            replaceFragment(((FragmentNavigationBundle) navigationBundle).getFragment());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("ASD", "Base Activity");

        if (resultCode == RESULT_OK) {
            //presenter.onNavigationResult(requestCode, RESULT_CODE_OK, data)

        } else {
            //presenter.onNavigationResult(requestCode, RESULT_CODE_CANCELLED, data)
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
     * Get whether activity has a toolbar.
     *
     * @return true if activity has a toolbar, false otherwise
     */
    protected boolean hasToolbar() {
        return false;
    }

    /**
     * Convenience method for replacing MTSFragments.
     *
     * @param fragment MTSFragment to be added
     */
    protected void replaceFragment(BaseFragment fragment) {
        replaceFragment(fragment, true);
    }

    /**
     * Method for replacing fragment with current one in base container.
     *
     * @param fragment       Fragment to be added
     * @param addToBackStack Whether this transaction to be added to back stack or not
     */
    protected void replaceFragment(BaseFragment fragment, boolean addToBackStack) {
        replaceFragment(fragment, fragment.getFragmentTag(), addToBackStack);
    }

    /**
     * Method for replacing fragment with the current one in the base container.
     *
     * @param fragment       Fragment to be added
     * @param tag            Tag to be used
     * @param addToBackStack Whether this transaction to be added to back stack or not
     */
    public void replaceFragment(Fragment fragment,
                                String tag,
                                boolean addToBackStack) {

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getBaseFrameLayoutId(), fragment, tag);

        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }

        transaction.commit();

    }

    public void restartActivity() {
        finish();
        startActivity(getIntent());
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
