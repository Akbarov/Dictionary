package com.example.hp_pk.dictionary.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by root on 1/25/18.
 */

public class PrefManager {
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

}
