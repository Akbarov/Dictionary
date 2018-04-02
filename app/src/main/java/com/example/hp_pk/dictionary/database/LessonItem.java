package com.example.hp_pk.dictionary.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Zohidjon
 * @since 2018, April 01
 */
@Entity(nameInDb = "lessonItem")
public class LessonItem {

    private String subject;

    private String level;

    private String lesson;

    @Index(unique = true)
    private String downloadUrl;

    private String name;

    @Generated(hash = 2010576322)
    public LessonItem(String subject, String level, String lesson,
            String downloadUrl, String name) {
        this.subject = subject;
        this.level = level;
        this.lesson = lesson;
        this.downloadUrl = downloadUrl;
        this.name = name;
    }

    @Generated(hash = 2127178043)
    public LessonItem() {
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLesson() {
        return this.lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
