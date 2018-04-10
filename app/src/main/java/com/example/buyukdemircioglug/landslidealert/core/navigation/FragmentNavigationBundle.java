package com.example.buyukdemircioglug.landslidealert.core.navigation;

import com.example.buyukdemircioglug.landslidealert.core.BaseFragment;

public class FragmentNavigationBundle extends NavigationBundle {

    private BaseFragment fragment;

    public FragmentNavigationBundle(BaseFragment fragment) {
        this.fragment = fragment;
    }

    public BaseFragment getFragment() {
        return fragment;
    }
}
