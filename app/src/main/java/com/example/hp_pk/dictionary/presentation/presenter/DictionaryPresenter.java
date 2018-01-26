package com.example.hp_pk.dictionary.presentation.presenter;

import android.view.View;
import android.widget.ImageView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.presentation.view.DictionaryView;

/**
 * Created by root on 1/25/18.
 */
@InjectViewState
public class DictionaryPresenter extends MvpPresenter<DictionaryView> {

    private DictionaryView stateView;
    private boolean isFavoriteSelected = false;

    public DictionaryPresenter() {
        stateView = getViewState();
    }

    public void backPressed() {
        stateView.backButtonPressed();
    }

    public void favoritePressed(View view) {
        stateView.favoriteButtonPressed();
        ((ImageView) view).setImageResource(isFavoriteSelected ? R.drawable.ic_star_half_24dp : R.drawable.ic_star_24dp);
        isFavoriteSelected = !isFavoriteSelected;
    }

    public void historyPressed() {
        stateView.historyButtonPressed();
    }

}
