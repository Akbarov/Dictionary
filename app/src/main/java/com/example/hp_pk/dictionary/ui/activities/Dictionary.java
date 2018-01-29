package com.example.hp_pk.dictionary.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.WordClass;
import com.example.hp_pk.dictionary.holder.WordViewHolder;
import com.example.hp_pk.dictionary.manager.DatabaseAccess;
import com.example.hp_pk.dictionary.presentation.presenter.DictionaryPresenter;
import com.example.hp_pk.dictionary.presentation.view.DictionaryView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @auther root on 1/25/18.
 */

public class Dictionary extends MvpAppCompatActivity implements DictionaryView {

    enum LANGUAGE{
        ENGLISH,UZBEK
    }

    @InjectPresenter
    DictionaryPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.switch_language)
    LinearLayout switchLanguage;

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    private RecyclerArrayAdapter<WordClass> adapter;

    public static void start(Context context) {
        context.startActivity(new Intent(context, Dictionary.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictionary_layout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, 2, 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<WordClass>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new WordViewHolder(parent);
            }
        });

        DatabaseAccess access = DatabaseAccess.getInstance(this);
        access.open();

        adapter.addAll(access.getWords(1));
        access.close();
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("sss", position + "");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                presenter.backPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void backButtonPressed() {
        onBackPressed();
    }

    @Override
    public void favoriteButtonPressed() {
        Toast.makeText(this, "Pressed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void historyButtonPressed() {

    }

    @OnClick(value = {R.id.favorite, R.id.history,R.id.switch_language})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.favorite:
                presenter.favoritePressed(view);
                break;
            case R.id.history:
                presenter.historyPressed();
                break;
            case R.id.switch_language:

        }
    }
}
