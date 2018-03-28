
package com.example.buyukdemircioglug.landslidealert.core;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hannesdorfmann.fragmentargs.FragmentArgs;

/**
 * Extend all your fragments from this base fragment.
 */
public abstract class BaseFragment extends Fragment implements BaseView<BasePresenter> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this); // read @Arg fields
    }

}
