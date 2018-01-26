package com.example.hp_pk.dictionary.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.presentation.view.MainView;

/**
 * @author HP-PK
 * @since 2018, January 23
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private MainView stateView;

    public MainPresenter() {
        stateView = getViewState();
        Dictionary.getAppComponent().inject(this);
    }

    public void myTutorButtonClicked() {
        Log.d("sss", "myTutor");
        stateView.myTutorButtonClicked();
    }

    public void dictionaryButtonClicked() {
        Log.d("sss", "dictionary");
        stateView.dictionaryButtonClicked();

    }

    public void booksButtonClicked() {
        Log.d("sss", "books");
        stateView.booksButtonClicked();

    }
}
