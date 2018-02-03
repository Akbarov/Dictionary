package com.example.hp_pk.dictionary.presentation.presenter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.WordClass;
import com.example.hp_pk.dictionary.holder.WordViewHolder;
import com.example.hp_pk.dictionary.listeners.ItemClickListener;
import com.example.hp_pk.dictionary.listeners.OnItemClickListener;
import com.example.hp_pk.dictionary.manager.DatabaseAccess;
import com.example.hp_pk.dictionary.manager.PrefManager;
import com.example.hp_pk.dictionary.presentation.view.DictionaryView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

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
    private ItemClickListener itemClickListener;
    private OnItemClickListener favoriteItemListener;

    public DictionaryPresenter() {
        Dictionary.getAppComponent().inject(this);
        stateView = getViewState();
        setUpListeners();
    }

    public RecyclerArrayAdapter<WordClass> getAdapter(Context context) {

        adapter = new RecyclerArrayAdapter<WordClass>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new WordViewHolder(parent, favoriteItemListener);
            }
        };

        adapter.setOnItemClickListener(itemClickListener);
        setDatabaseAccess(context);
        setAllWords(access.getWords(prefManager.getLanguageType(), prefManager.isFavorite(), offset));
        stateView.switchTitleLanguage(prefManager.getLanguageType());
        return adapter;
    }

    private void setUpListeners() {
        itemClickListener = new ItemClickListener(position -> {
            Log.d("sss", position + "");
            WordClass wordClass = adapter.getItem(position);
            if (wordClass != null) {
                access.updateLastUsedDate(wordClass.getId());
                //show meaning here
            }

        });
        favoriteItemListener = (position, word) -> {
            access.updateFavoriteState(word.getId(), word.isFavorite() ? 1 : 0);
        };
    }

    private void setAllWords(List<WordClass> wordList) {
        adapter.clear();
        adapter.addAll(wordList);
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

    public void favoritePressed() {
        changeFavoriteIconState(prefManager.isFavorite());
        changeHistoryIconState(true);
        setAllWords(access.getWords(prefManager.getLanguageType(), prefManager.isFavorite(), offset));
    }

    public void historyPressed() {
        changeHistoryIconState(prefManager.isLastWordsShown());
        changeFavoriteIconState(true);
        if (!prefManager.isLastWordsShown()) {
            setAllWords(access.getLastWords(prefManager.getLanguageType()));
        } else
            setAllWords(access.getWords(prefManager.getLanguageType(), prefManager.isFavorite(), offset));
    }

    private void changeHistoryIconState(boolean history) {
        history = !history;
        prefManager.setHistory(history);
        stateView.historyButtonPressed(history);
    }

    private void changeFavoriteIconState(boolean favorite) {
        favorite = !favorite;
        prefManager.setFavorite(favorite);
        stateView.favoriteButtonPressed(favorite);
    }

    public void setUpSearchView(EditText searchText) {
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    //setAllWords(prefManager.getLanguageType(), prefManager.isFavorite(), offset);
                    stateView.showHideClearButton(true);
                } else {
                    setAllWordsWithFilter(prefManager.getLanguageType(), prefManager.isFavorite(), charSequence.toString());
                    stateView.showHideClearButton(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void switchTitleLanguage() {
        stateView.switchTitleLanguage(prefManager.getLanguageType());
    }

    public void switchLanguage() {
        prefManager.setLanguageType(prefManager.getLanguageType() == 1 ? 0 : 1);
        setAllWords(access.getWords(prefManager.getLanguageType(), prefManager.isFavorite(), offset));
        switchTitleLanguage();
        changeHistoryIconState(true);
    }

    public void clearButtonPressed() {
        offset = 0;
        setAllWords(access.getWords(prefManager.getLanguageType(), prefManager.isFavorite(), offset));
    }
}
