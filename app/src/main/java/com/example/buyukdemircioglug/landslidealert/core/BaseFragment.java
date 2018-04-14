
package com.example.buyukdemircioglug.landslidealert.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buyukdemircioglug.landslidealert.core.navigation.ActivityNavigationBundle;
import com.example.buyukdemircioglug.landslidealert.core.navigation.FragmentNavigationBundle;
import com.example.buyukdemircioglug.landslidealert.core.navigation.NavigationBundle;
import com.hannesdorfmann.fragmentargs.FragmentArgs;

import butterknife.ButterKnife;

/**
 * Extend all your fragments from this base fragment.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this); // read @Arg fields
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(getResourceLayoutId(), container, false);

        ButterKnife.bind(this, rootView);

        initUserInterface(inflater, rootView);

        return rootView;
    }


    public void handleNavigation(NavigationBundle navigationBundle) {
        if (navigationBundle instanceof ActivityNavigationBundle) {
            if (((ActivityNavigationBundle) navigationBundle).isRestartCurrentActivity()) {
                ((BaseActivity) getActivity()).restartActivity();

            } else if (((ActivityNavigationBundle) navigationBundle).isFinishCurrentActivity()) {
                getActivity().finish();
            }

        } else if(navigationBundle instanceof FragmentNavigationBundle) {
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
    protected abstract void initUserInterface(LayoutInflater inflater, final View rootView);
}
