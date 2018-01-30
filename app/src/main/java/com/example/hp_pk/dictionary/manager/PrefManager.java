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

    public void setLanguageType(int type) {
        prefsHelper.put(LANGUAGE, type);
    }

    public int getLanguageType() {
        return prefsHelper.get(LANGUAGE, 1);
    }

    public void setFavorite(boolean type) {
        prefsHelper.put(FAVORITE, type);
    }

    public boolean isFavorite() {
        return prefsHelper.get(FAVORITE, false);
    }
}
