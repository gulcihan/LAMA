package com.example.buyukdemircioglug.landslidealert.core.navigation;

import android.support.annotation.NonNull;

import com.example.buyukdemircioglug.landslidealert.core.BaseFragment;
import com.example.buyukdemircioglug.landslidealert.core.BasePresenter;

public class FragmentNavigationBundle extends NavigationBundle {

    private BaseFragment fragment;
    private BasePresenter presenter;

    public FragmentNavigationBundle(@NonNull BaseFragment fragment, @NonNull BasePresenter presenter) {
        this.fragment = fragment;
        this.presenter = presenter;
    }

    public BaseFragment getFragment() {
        return fragment;
    }

    public BasePresenter getPresenter() {
        return presenter;
    }

}
