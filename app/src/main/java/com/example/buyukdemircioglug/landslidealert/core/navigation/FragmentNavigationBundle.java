package com.example.buyukdemircioglug.landslidealert.core.navigation;

import com.example.buyukdemircioglug.landslidealert.core.BaseFragment;
import com.example.buyukdemircioglug.landslidealert.core.BasePresenter;

public class FragmentNavigationBundle extends NavigationBundle {

    private BaseFragment fragment;

    private BasePresenter presenter;

    public FragmentNavigationBundle(BaseFragment fragment,
                                    BasePresenter presenter) {
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
