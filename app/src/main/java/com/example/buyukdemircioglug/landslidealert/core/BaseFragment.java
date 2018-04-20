
package com.example.buyukdemircioglug.landslidealert.core;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.canelmas.let.DeniedPermission;
import com.canelmas.let.Let;
import com.canelmas.let.RuntimePermissionListener;
import com.canelmas.let.RuntimePermissionRequest;
import com.example.buyukdemircioglug.landslidealert.core.navigation.ActivityNavigationBundle;
import com.example.buyukdemircioglug.landslidealert.core.navigation.FragmentNavigationBundle;
import com.example.buyukdemircioglug.landslidealert.core.navigation.NavigationBundle;
import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateFragment;
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;

/**
 * Extend all your fragments from this base fragment.
 */
public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>, VS extends ViewState<V>>
        extends MvpViewStateFragment<V, P, VS> implements RuntimePermissionListener {

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Icepick.restoreInstanceState(this, savedInstanceState);

        final View rootView = inflater.inflate(getResourceLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initUserInterface(inflater, rootView);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Let.handle(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionDenied(List<DeniedPermission> deniedPermissionList) {

    }

    @Override
    public void onShowPermissionRationale(List<String> list, RuntimePermissionRequest runtimePermissionRequest) {
        runtimePermissionRequest.retry();
    }

    public void handleNavigation(NavigationBundle navigationBundle) {
        if (navigationBundle instanceof ActivityNavigationBundle) {

            final ActivityNavigationBundle bundle = (ActivityNavigationBundle) navigationBundle;

            if (bundle.isRestartCurrentActivity()) {
                ((BaseActivity) getActivity()).restartActivity();

            } else if (bundle.isFinishCurrentActivity()) {
                getActivity().finish();

            } else if (bundle.isRequestCodeValid(bundle.getRequestCode())) {
                startActivityForResult(bundle.getIntent(), bundle.getRequestCode());
            }

        } else if (navigationBundle instanceof FragmentNavigationBundle) {
            ((BaseActivity) getActivity()).handleNavigation(navigationBundle);
        }
    }

    public String getFragmentTag() {
        return this.getClass().getSimpleName();
    }

    public void setToolbarTitle(String title) {
        ((BaseActivity) getActivity()).getToolBar().setTitle(title);
    }

    /**
     * Resource layout id of the fragment.
     * Child fragments must override this to have a layout.
     *
     * @return resource layout
     */
    protected abstract int getResourceLayoutId();

    /**
     * Initialize UI content elements.
     *
     * @param inflater layout inflater of fragment
     * @param rootView The fragment's root view
     */
    protected abstract void initUserInterface(LayoutInflater inflater, final android.view.View rootView);
}
