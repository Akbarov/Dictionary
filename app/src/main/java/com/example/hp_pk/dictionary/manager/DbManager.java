package com.example.hp_pk.dictionary.manager;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * @auther root
 * @since 1/26/18.
 */

public class DbManager {
    Context context;
    private AppDatabase db;

    public DbManager(Context context) {
        this.context = context;
        makeDataBase();
    }

    private void makeDataBase() {
        db = Room.databaseBuilder(context,
                AppDatabase.class, "database").build();
    }
}
