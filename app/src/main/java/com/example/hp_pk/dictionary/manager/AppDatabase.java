package com.example.hp_pk.dictionary.manager;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.hp_pk.dictionary.Dao.SubjectDao;

/**
 * @author HP-PK
 * @since 2018, February 09
 */

@Database(entities = {Subjects.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SubjectDao subjectDao();
}
