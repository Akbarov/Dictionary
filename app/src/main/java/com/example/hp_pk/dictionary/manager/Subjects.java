package com.example.hp_pk.dictionary.manager;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author HP-PK
 * @since 2018, February 09
 */

@Entity(tableName = "subjects")
public class Subjects {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "subject")
    private String subject;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
