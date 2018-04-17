package com.example.buyukdemircioglug.landslidealert.core;

import com.example.buyukdemircioglug.landslidealert.core.navigation.NavigationBundle;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface BaseView extends MvpView {

    void handleNavigation(NavigationBundle navigationBundle);
}
