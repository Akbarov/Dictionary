package com.example.hp_pk.dictionary.room_database.dao;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.hp_pk.dictionary.manager.Subjects;

import java.util.List;

/**
 * @auther root
 * @since 2/9/18.
 */

public interface SubjectDao {
    @Query("select * from subjects")
    List<Subjects> getAll();

    @Query("select * from subjects where sId ==:sId")
    Subjects getSubject(int sId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Subjects> subjects);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Subjects subjects);
}
