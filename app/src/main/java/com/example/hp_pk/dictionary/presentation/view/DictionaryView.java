package com.example.hp_pk.dictionary.presentation.view;

import android.support.v7.view.menu.MenuView;

import com.arellomobile.mvp.MvpView;

/**
 * Created by root on 1/25/18.
 */

public interface DictionaryView extends MvpView {

    void backButtonPressed();

    void favoriteButtonPressed(boolean isFavorites);

    void historyButtonPressed(boolean isLastWordShown);

    void switchTitleLanguage(int type);

    void showHideClearButton(boolean hide);

}
