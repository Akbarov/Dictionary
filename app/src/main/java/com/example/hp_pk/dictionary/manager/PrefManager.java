package com.example.hp_pk.dictionary.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.hp_pk.dictionary.utils.Constants;

/**
 * Created by root on 1/25/18.
 */

public class PrefManager implements Constants {
    private Context context;
    private SharedPrefsHelper prefsHelper;

    public PrefManager(Context context) {
        this.context = context;
        makePreference();
    }

    private void makePreference() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        prefsHelper = new SharedPrefsHelper(preferences);
    }

    public int getLanguageType() {
        return prefsHelper.get(LANGUAGE, 1);
    }

    public void setLanguageType(int type) {
        prefsHelper.put(LANGUAGE, type);
    }

    public boolean isFavorite() {
        return prefsHelper.get(FAVORITE, false);
    }

    public void setFavorite(boolean type) {
        prefsHelper.put(FAVORITE, type);
    }

    public void setHistory(boolean isLastWordsShown) {
        prefsHelper.put(HISTORY, isLastWordsShown);
    }

    public boolean isLastWordsShown() {
        return prefsHelper.get(HISTORY, false);
    }

    public long getLastBookUpdated(String category) {
        return prefsHelper.get(LAST_BOOK_UPDATED + category, 0L);
    }

    public void setLastBookUpdated(String category, long lastBookUpdated) {
        prefsHelper.put(LAST_BOOK_UPDATED + category, lastBookUpdated);
    }

    public long getLastMyTutorUpdated(String category) {
        return prefsHelper.get(LAST_MyTutor_UPDATED + category, 0L);
    }

    public void setLastMyTutorUpdated(String category, long lastBookUpdated) {
        prefsHelper.put(LAST_MyTutor_UPDATED + category, lastBookUpdated);
    }

    public int getSizeOfBooks() {
        return prefsHelper.get(SIZE_OF_BOOKS, 0);
    }

    public void setSizeOfBooks(int sizeOfBooks) {
        prefsHelper.put(SIZE_OF_BOOKS, sizeOfBooks);
    }

}
