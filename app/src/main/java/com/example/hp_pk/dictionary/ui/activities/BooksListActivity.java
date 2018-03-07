package com.example.hp_pk.dictionary.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther ZOHIDJON
 * @since 2/28/18.
 */

public class BooksListActivity extends MvpAppCompatActivity implements BooksListView, MaterialSearchView.SearchViewListener, MaterialSearchView.OnQueryTextListener {

    @InjectPresenter
    BooksListPresenter presenter;

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    @BindView(R.id.search_view)
    MaterialSearchView searchView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void start(Context context) {
        context.startActivity(new Intent(context, BooksListActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list_layout);
        ButterKnife.bind(this);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.color_cursor_white);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Books");

        toolbar.setNavigationOnClickListener(v -> finish());

        searchView.setOnSearchViewListener(this);
        searchView.setOnQueryTextListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.books_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onSearchViewShown() {

    }

    @Override
    public void onSearchViewClosed() {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        presenter.searchWithFilter(newText);
        return true;
    }
}
