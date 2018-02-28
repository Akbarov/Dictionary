package com.example.hp_pk.dictionary.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.database.Book;
import com.example.hp_pk.dictionary.holder.BookViewHolder;
import com.example.hp_pk.dictionary.presentation.presenter.BooksListPresenter;
import com.example.hp_pk.dictionary.presentation.view.BooksListView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther ZOHIDJON
 * @since 2/28/18.
 */

public class BooksListActivity extends MvpAppCompatActivity implements BooksListView {

    @InjectPresenter
    BooksListPresenter presenter;

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    public static void start(Context context) {
        context.startActivity(new Intent(context, BooksListActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list_layout);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        createAdapter();
    }

    private void createAdapter() {
        RecyclerArrayAdapter<Book> adapter = new RecyclerArrayAdapter<Book>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new BookViewHolder(parent, BooksListActivity.this);
            }
        };
        presenter.setAdapter(adapter);
        recyclerView.setAdapterWithProgress(adapter);
    }
}
