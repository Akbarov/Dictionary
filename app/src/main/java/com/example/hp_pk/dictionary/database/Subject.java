package com.example.hp_pk.dictionary.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @auther ZOHIDJON
 * @since 2/20/18.
 */

@Entity(nameInDb = "subject")
public class Subject {

    @Index(unique = true)
    private int id;

    @NotNull
    private String subject;

    @Generated(hash = 2087094757)
    public Subject(int id, @NotNull String subject) {
        this.id = id;
        this.subject = subject;
    }

    @Generated(hash = 1617906264)
    public Subject() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
}
