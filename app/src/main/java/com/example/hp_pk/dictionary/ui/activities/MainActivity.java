package com.example.hp_pk.dictionary.ui.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.presentation.presenter.MainPresenter;
import com.example.hp_pk.dictionary.presentation.view.MainView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

    }

    @OnClick(value = {R.id.my_tutor, R.id.dictionary, R.id.books})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_tutor:
                presenter.myTutorButtonClicked();
                break;
            case R.id.dictionary:
                presenter.dictionaryButtonClicked();
                break;
            case R.id.books:
                presenter.booksButtonClicked();
                break;
        }
    }

    @Override
    public void myTutorButtonClicked() {
        MyTutor.start(this);
    }

    @Override
    public void dictionaryButtonClicked() {
        DictionaryActivity.start(this);
    }

    @Override
    public void booksButtonClicked() {
        BooksListActivity.start(this);
    }
}
