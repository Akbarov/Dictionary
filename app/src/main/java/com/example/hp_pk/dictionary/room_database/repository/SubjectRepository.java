package com.example.hp_pk.dictionary.room_database.repository;

import com.example.hp_pk.dictionary.manager.Subjects;
import com.example.hp_pk.dictionary.room_database.dao.SubjectDao;

import java.util.List;

/**
 * @auther root
 * @since 2/12/18.
 */

public interface SubjectRepository {

    void insertAll(List<Subjects> list);

    void insert(Subjects subjects);

    List<Subjects> getAll();

}
