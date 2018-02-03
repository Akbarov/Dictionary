package com.example.hp_pk.dictionary.manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hp_pk.dictionary.WordClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HP-PK
 * @since 2018, January 27
 */

public class DatabaseAccess {
    private static DatabaseAccess instance;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new MyBatabase(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<WordClass> getWords(int type) {
        List<WordClass> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM words where type ==" + type, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new WordClass(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5) == 1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
