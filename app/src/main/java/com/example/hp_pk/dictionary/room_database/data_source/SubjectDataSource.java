package com.example.hp_pk.dictionary.room_database.data_source;

import com.example.hp_pk.dictionary.manager.Subjects;
import com.example.hp_pk.dictionary.room_database.dao.SubjectDao;
import com.example.hp_pk.dictionary.room_database.repository.SubjectRepository;

import java.util.List;

/**
 * @auther root
 * @since 2/9/18.
 */

public class SubjectDataSource implements SubjectRepository {
    private SubjectDao subjectDao;

    public SubjectDataSource(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }
    @Override
    public void insertAll(List<Subjects> list) {
        subjectDao.insertAll(list);
    }

    @Override
    public void insert(Subjects subjects) {
        subjectDao.insert(subjects);
    }

    @Override
    public List<Subjects> getAll() {
        return subjectDao.getAll();
    }
}
