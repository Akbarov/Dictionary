package com.example.hp_pk.dictionary.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @auther ZOHIDJON
 * @since 3/29/18.
 */

@Entity(nameInDb = "photo")
public class Photo {

    @Index(unique = true)
    private String id;

    private String downloadUrl;

    private String name;

    @Generated(hash = 1876741901)
    public Photo(String id, String downloadUrl, String name) {
        this.id = id;
        this.downloadUrl = downloadUrl;
        this.name = name;
    }

    @Generated(hash = 1043664727)
    public Photo() {
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
