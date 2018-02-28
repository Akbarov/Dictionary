package com.example.hp_pk.dictionary.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.database.DbManager;
import com.example.hp_pk.dictionary.database.Subject;
import com.example.hp_pk.dictionary.presentation.view.MyTutorView;

import java.util.List;

import javax.inject.Inject;

/**
 * @auther ZOHIDJON
 * @since 2/12/18.
 */
@InjectViewState
public class MyTutorPresenter extends MvpPresenter<MyTutorView> {

    private MyTutorView stateView;

    @Inject
    DbManager manager;

    public MyTutorPresenter() {
        Dictionary.getAppComponent().inject(this);
        stateView = getViewState();
    }

    private void showAllSubjectsInUI() {
        stateView.setAllSubjects(manager.getAllSubjects());
    }

    public void setAllSubjects(List<Subject> list) {
        showAllSubjectsInUI();
        for (Subject subject : list) {
            manager.setSubject(subject);
        }
    }


}
