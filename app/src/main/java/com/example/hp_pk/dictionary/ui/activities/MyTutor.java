package com.example.hp_pk.dictionary.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.database.Book;
import com.example.hp_pk.dictionary.database.Subject;
import com.example.hp_pk.dictionary.holder.BookViewHolder;
import com.example.hp_pk.dictionary.presentation.presenter.BooksListPresenter;
import com.example.hp_pk.dictionary.presentation.presenter.MyTutorPresenter;
import com.example.hp_pk.dictionary.presentation.view.BooksListView;
import com.example.hp_pk.dictionary.presentation.view.MyTutorView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther root
 * @since 1/29/18.
 */

public class MyTutor extends MvpAppCompatActivity implements BooksListView {

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
        setUpPager();
    }

    public void setUpPager() {
        if (presenter.hasCategory()) {
            tabLayout.setupWithViewPager(pager);
            pager.setAdapter(presenter.getPagerAdapter(getSupportFragmentManager()));
            progressBar.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void startBookActivity(Book book) {
        BookActivity.start(this, book);
    }

    @Override
    public void getBookListCanceled(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateCategories() {
        setUpPager();
    }
}
