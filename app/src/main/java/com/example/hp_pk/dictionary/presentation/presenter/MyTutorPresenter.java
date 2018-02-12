package com.example.hp_pk.dictionary.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.hp_pk.dictionary.manager.Subjects;
import com.example.hp_pk.dictionary.presentation.view.MyTutorView;
import com.example.hp_pk.dictionary.room_database.repository.SubjectRepository;

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
    SubjectRepository subjectRepository;

    public MyTutorPresenter() {
        stateView = getViewState();
    }

    private void showAllSubjectsInUI() {
        stateView.setAllSubjects(subjectRepository.getAll());
    }

    public void setAllSubjects(List<Subjects> list) {
        subjectRepository.insertAll(list);
        showAllSubjectsInUI();
    }

}
