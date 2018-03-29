package com.example.hp_pk.dictionary.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @auther ZOHIDJON
 * @since 3/29/18.
 */

@Entity(nameInDb = "audio")
public class Audio {

    @Index(unique = true)
    private String id;

    private String downloadUrl;

    private String name;

    @Generated(hash = 1090709830)
    public Audio(String id, String downloadUrl, String name) {
        this.id = id;
        this.downloadUrl = downloadUrl;
        this.name = name;
    }

    @Generated(hash = 1642629471)
    public Audio() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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
