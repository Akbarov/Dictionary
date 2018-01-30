package com.example.hp_pk.dictionary.presentation.presenter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.WordClass;
import com.example.hp_pk.dictionary.holder.WordViewHolder;
import com.example.hp_pk.dictionary.listeners.ItemClickListener;
import com.example.hp_pk.dictionary.manager.DatabaseAccess;
import com.example.hp_pk.dictionary.manager.PrefManager;
import com.example.hp_pk.dictionary.presentation.view.DictionaryView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;


import javax.inject.Inject;

/**
 * Created by root on 1/25/18.
 */
@InjectViewState
public class DictionaryPresenter extends MvpPresenter<DictionaryView> {

    private DictionaryView stateView;
    private RecyclerArrayAdapter<WordClass> adapter;
    private DatabaseAccess access;
    private int offset = 0;
    @Inject
    PrefManager prefManager;

    public DictionaryPresenter() {
        Dictionary.getAppComponent().inject(this);
        stateView = getViewState();
    }

    public RecyclerArrayAdapter<WordClass> getAdapter(Context context) {
        adapter = new RecyclerArrayAdapter<WordClass>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new WordViewHolder(parent);
            }

        };
        ItemClickListener listener = new ItemClickListener(new ItemClickListener.ItemClickedListener() {
            @Override
            public void clicked(int position) {
                Log.d("sss", position + "");
            }
        });
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(listener);
        setDatabaseAccess(context);
        setAllWords(prefManager.getLanguageType(), prefManager.isFavorite(), offset);
        return adapter;
    }

    private void setAllWords(int type, boolean isFavorite, int offset) {
        adapter.clear();
        adapter.addAll(access.getWords(type, isFavorite, offset));
    }

    private void setAllWordsWithFilter(int type, boolean isFavorite, String filter) {
        adapter.clear();
        adapter.addAll(access.getWordsWithFilter(type, filter));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        access.close();
    }

    private void setDatabaseAccess(Context context) {
        access = DatabaseAccess.getInstance(context);
        access.open();
    }

    public void backPressed() {
        stateView.backButtonPressed();
    }

    public void favoritePressed(View view) {
        stateView.favoriteButtonPressed();
        ((ImageView) view).setImageResource(!prefManager.isFavorite() ? R.drawable.ic_star_half_24dp : R.drawable.ic_star_24dp);
        prefManager.setFavorite(!prefManager.isFavorite());
        setAllWords(prefManager.getLanguageType(), prefManager.isFavorite(), offset);
    }

    public void historyPressed() {
        stateView.historyButtonPressed();
    }

    public void setUpSearchView(EditText searchText) {
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    setAllWords(prefManager.getLanguageType(), prefManager.isFavorite(), offset);
                } else
                    setAllWordsWithFilter(prefManager.getLanguageType(), prefManager.isFavorite(), charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void switchLanguage() {
        adapter.clear();
        adapter.addAll(access.getWords(prefManager.getLanguageType(), prefManager.isFavorite(), offset));
    }
}
