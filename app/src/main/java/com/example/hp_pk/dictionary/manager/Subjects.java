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
    private String id;

    @ColumnInfo(name = "subject")
    private String subject;

    public Subjects(String id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
