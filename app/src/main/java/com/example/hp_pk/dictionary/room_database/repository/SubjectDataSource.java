package com.example.hp_pk.dictionary.room_database.repository;

import com.example.hp_pk.dictionary.room_database.dao.SubjectDao;

import java.util.List;

import javax.inject.Inject;

/**
 * @auther root
 * @since 2/9/18.
 */

public class SubjectDataSource {
    private SubjectDao subjectDao;

    @Inject
    public SubjectDataSource(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }


}
