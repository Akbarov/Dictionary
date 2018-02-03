package com.example.hp_pk.dictionary.manager;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * @auther root
 * @since 1/29/18.
 */

public class MyDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "myBd.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}