package com.example.buyukdemircioglug.landslidealert.core;

import com.example.buyukdemircioglug.landslidealert.core.navigation.NavigationBundle;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void handleNavigation(NavigationBundle navigationBundle);
}
