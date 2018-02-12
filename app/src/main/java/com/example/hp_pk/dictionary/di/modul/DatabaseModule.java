package com.example.hp_pk.dictionary.di.modul;


import android.arch.persistence.room.Room;

import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.room_database.AppDatabase;
import com.example.hp_pk.dictionary.room_database.dao.SubjectDao;
import com.example.hp_pk.dictionary.room_database.data_source.SubjectDataSource;
import com.example.hp_pk.dictionary.room_database.repository.SubjectRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @auther root
 * @since 2/9/18.
 */
@Module
public class DatabaseModule {

    private AppDatabase database;

    DatabaseModule(Dictionary dictionary) {
        database =  Room.databaseBuilder(dictionary, AppDatabase.class, "room_db").build();
    }

    @Singleton
    @Provides
    AppDatabase provideRoomDatabase() {
        return database;
    }

    @Singleton
    @Provides
    SubjectDao provideSubjectDao(AppDatabase database) {
        return database.subjectDao();
    }

    @Singleton
    @Provides
    SubjectRepository getSubjectRepository(SubjectDao subjectDao) {
        return new SubjectDataSource(subjectDao);
    }

}
