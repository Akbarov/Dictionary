package com.example.hp_pk.dictionary.room_database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.hp_pk.dictionary.manager.Subjects;

import java.util.List;

/**
 * @auther root
 * @since 2/9/18.
 */
@Dao
public interface SubjectDao {
    @Query("select * from subjects")
    List<Subjects> getAll();

    @Query("select * from subjects where id ==:id")
    Subjects getSubject(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Subjects> subjects);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Subjects subjects);
}
