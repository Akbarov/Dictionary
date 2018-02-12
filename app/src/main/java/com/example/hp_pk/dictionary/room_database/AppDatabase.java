package com.example.hp_pk.dictionary.room_database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.hp_pk.dictionary.manager.Subjects;
import com.example.hp_pk.dictionary.room_database.dao.SubjectDao;

/**
 * @author HP-PK
 * @since 2018, February 09
 */

@Database(entities = {Subjects.class}, version = AppDatabase.VERSION)
public abstract class AppDatabase extends RoomDatabase {

    static final short VERSION = 1;

    public abstract SubjectDao subjectDao();
}
