package com.example.hp_pk.dictionary.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

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

    @BindView(R.id.view_pager)
    ViewPager pager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private String category;

    public static void start(Context context) {
        context.startActivity(new Intent(context, BooksListActivity.class));
    }

    public static void start(Context context, String category) {
        Intent intent = new Intent(context, BooksListActivity.class);
        intent.putExtra("category", category);
        context.startActivity(intent);
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
        if (getIntent() != null) {
            this.category = getIntent().getStringExtra("category");
        }
        setTitle("Library");

        toolbar.setNavigationOnClickListener(v -> finish());
        createAdapter();
        setUpPager();
        searchView.setOnSearchViewListener(this);
        searchView.setOnQueryTextListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.GONE);
    }

    public void setUpPager() {
        if (presenter.hasCategory()) {
            tabLayout.setupWithViewPager(pager);
            pager.setAdapter(presenter.getPagerAdapter(getSupportFragmentManager()));
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void createAdapter() {
        RecyclerArrayAdapter<Book> adapter = new RecyclerArrayAdapter<Book>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new BookViewHolder(parent, BooksListActivity.this);
            }
        };
        presenter.setAdapter(adapter, category.isEmpty() ? getString(R.string.lessons) : category);
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
        pager.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSearchViewClosed() {
        recyclerView.setVisibility(View.GONE);
        pager.setVisibility(View.VISIBLE);
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
