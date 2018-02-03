package com.example.hp_pk.dictionary.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * @author HP-PK
 * @since 2018, January 27
 */

public class MyBatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "myBd.db";
    private static final int DATABASE_VERSION = 1;

    public MyBatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
