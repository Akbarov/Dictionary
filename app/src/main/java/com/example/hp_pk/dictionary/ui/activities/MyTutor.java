package com.example.hp_pk.dictionary.ui.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.adapters.LessonViewPagerAdapter;
import com.example.hp_pk.dictionary.adapters.MyTutorViewPagerAdapter;
import com.example.hp_pk.dictionary.database.Book;
import com.example.hp_pk.dictionary.presentation.presenter.MyTutorPresenter;
import com.example.hp_pk.dictionary.presentation.view.MyTutorView;
import com.jude.easyrecyclerview.EasyRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther root
 * @since 1/29/18.
 */

public class MyTutor extends MvpAppCompatActivity implements MyTutorView {

    @InjectPresenter
    MyTutorPresenter presenter;

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.view_pager)
    ViewPager pager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private String category = "";
    private ProgressDialog progressDialog;


    public static void start(Context context) {
        context.startActivity(new Intent(context, MyTutor.class));
    }

    public static void start(Context context, String category) {
        Intent intent = new Intent(context, MyTutor.class);
        intent.putExtra("category", category);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list_layout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (getIntent() != null) {
            this.category = getIntent().getStringExtra("category");
        }
        recyclerView.setVisibility(View.GONE);
        setTitle("My Center");
        toolbar.setNavigationOnClickListener(v -> finish());
        presenter.updateBooksFromServer(category);
        createDialog();
        setUpPager();
    }

    private void createDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
    }

    @Override
    public void setUpPager() {
        if (presenter.hasCategory()) {
            tabLayout.setupWithViewPager(pager);
            presenter.setPagerAdapter(getSupportFragmentManager());
            progressDialog.hide();
        } else {
            progressDialog.show();
        }
    }

    @Override
    public void startBookActivity(Book book) {
        BookActivity.start(this, book);
    }

    @Override
    protected void onStop() {
        super.onStop();
        progressDialog.dismiss();
    }

    @Override
    public void getBookListCanceled(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAdapter(LessonViewPagerAdapter adapter) {
        pager.setAdapter(adapter);
    }

    @Override
    public void setAdapter(MyTutorViewPagerAdapter adapter) {
        pager.setAdapter(adapter);
    }
}
