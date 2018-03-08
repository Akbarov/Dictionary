package com.example.hp_pk.dictionary.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.classes.WordClass;
import com.example.hp_pk.dictionary.manager.DatabaseAccess;
import com.example.hp_pk.dictionary.manager.PrefManager;
import com.example.hp_pk.dictionary.presentation.presenter.DictionaryPresenter;
import com.example.hp_pk.dictionary.presentation.view.DictionaryView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @auther root on 1/25/18.
 */

public class DictionaryActivity extends MvpAppCompatActivity implements DictionaryView {

    private DatabaseAccess access;

    @InjectPresenter
    DictionaryPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.switch_language)
    LinearLayout switchLanguage;

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    @BindView(R.id.search_edit_text)
    EditText searchText;

    @BindView(R.id.clear_btn)
    ImageView clearBtn;

    @BindView(R.id.from_language)
    TextView fromLanguage;

    @BindView(R.id.to_language)
    TextView toLanguage;

    @BindView(R.id.favorite)
    ImageView favoriteIcon;

    @BindView(R.id.history)
    ImageView historyIcon;

    @Inject
    PrefManager prefManager;

    private RecyclerArrayAdapter<WordClass> adapter;

    public static void start(Context context) {
        context.startActivity(new Intent(context, DictionaryActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictionary_layout);
        Dictionary.getAppComponent().inject(this);
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
        recyclerView.setAdapterWithProgress(presenter.getAdapter(this));
        presenter.setUpSearchView(searchText);

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
    public void favoriteButtonPressed(boolean isFavoritesShown) {
        Toast.makeText(this, "Pressed", Toast.LENGTH_SHORT).show();
        favoriteIcon.setImageResource(isFavoritesShown ? R.drawable.ic_star_half_24dp : R.drawable.ic_star_24dp);
    }

    @Override
    public void historyButtonPressed(boolean isLastWordsShown) {
        //history icon changes
        historyIcon.setImageResource(isLastWordsShown?R.drawable.ic_cancel_24dp : R.drawable.ic_history_24dp);
    }

    @Override
    public void switchTitleLanguage(int type) {
        searchText.setText("");
        if (type == 1) {
            fromLanguage.setText(R.string.english_short);
            toLanguage.setText(R.string.uzbek_short);
        } else {
            fromLanguage.setText(R.string.uzbek_short);
            toLanguage.setText(R.string.english_short);
        }
    }

    @Override
    public void showHideClearButton(boolean hide) {
        clearBtn.setVisibility(hide ? View.INVISIBLE : View.VISIBLE);
    }

    @OnClick(value = {R.id.favorite, R.id.history, R.id.switch_language, R.id.clear_btn})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.favorite:
                presenter.favoritePressed();
                break;
            case R.id.history:
                presenter.historyPressed();
                break;
            case R.id.switch_language:
                presenter.switchLanguage();
                break;
            case R.id.clear_btn:
                searchText.setText("");
                presenter.clearButtonPressed();
        }
    }

}
