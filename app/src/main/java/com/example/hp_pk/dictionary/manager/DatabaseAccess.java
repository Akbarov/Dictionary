package com.example.hp_pk.dictionary.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hp_pk.dictionary.classes.WordClass;
import com.example.hp_pk.dictionary.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther root
 * @since 1/29/18.
 */

public class DatabaseAccess implements Constants {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new MyDatabase(context);
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
     * Read all words from the database.
     *
     * @return a List of words
     */
    public List<WordClass> getWords(int type, boolean isFavorites, int offset) {
        List<WordClass> list = new ArrayList<>();
        Cursor cursor;
        if (isFavorites) {
            cursor = database.rawQuery("SELECT * FROM words where type =? and is_favorite =? order by word ASC", new String[]{String.valueOf(type), String.valueOf(1)});
        } else
            cursor = database.rawQuery("SELECT * FROM words where type =? order by word ASC", new String[]{String.valueOf(type)});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new WordClass(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getLong(4), cursor.getInt(5) == 1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<WordClass> getWordsWithFilter(int type, String filter) {
        List<WordClass> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM words where type =? and word like ? order by word ASC", new String[]{String.valueOf(type), "%" + filter + "%"});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new WordClass(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getLong(4), cursor.getInt(5) == 1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<WordClass> getLastWords(int type) {
        List<WordClass> wordList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM words where type =? and used_date >0 order by used_date DESC", new String[]{String.valueOf(type)});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            wordList.add(new WordClass(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getLong(4), cursor.getInt(5) == 1));
            cursor.moveToNext();
        }
        cursor.close();
        return wordList;
    }

    public void updateLastUsedDate(int wordId) {
        ContentValues values = new ContentValues();
        values.put(WORD_LAST_USED_DATE, System.currentTimeMillis());
        database.update(WORDS_TABLE_NAME, values, WORD_ID + "=" + wordId, null);
    }
    public void updateFavoriteState(int wordId,int state) {
        ContentValues values = new ContentValues();
        values.put(WORD_IS_FAVORITE, state);
        database.update(WORDS_TABLE_NAME, values, WORD_ID + "=" + wordId, null);
    }
}
