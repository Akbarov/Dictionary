package com.example.hp_pk.dictionary.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Zohidjon
 * @since 2018, April 01
 */
@Entity(nameInDb = "testItem")
public class TestItem {

    private String subject;

    private String level;

    private String name;

    @Index(unique = true)
    private String downloadUrl;

    @Generated(hash = 1596990530)
    public TestItem(String subject, String level, String name, String downloadUrl) {
        this.subject = subject;
        this.level = level;
        this.name = name;
        this.downloadUrl = downloadUrl;
    }

    @Generated(hash = 1690607865)
    public TestItem() {
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

}
