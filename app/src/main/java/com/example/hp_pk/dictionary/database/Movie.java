package com.example.hp_pk.dictionary.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

/**
 * @auther ZOHIDJON
 * @since 3/29/18.
 */

@Entity(nameInDb = "movie")
public class Movie {

    @Index(unique = true)
    private String id;

    private String downloadUrl;

    private String name;

    private String imageUrl;

    private int downloadState; //-1 no downloaded, 0 downloading,1downloaded

    @Generated(hash = 1947736616)
    public Movie(String id, String downloadUrl, String name, String imageUrl,
            int downloadState) {
        this.id = id;
        this.downloadUrl = downloadUrl;
        this.name = name;
        this.imageUrl = imageUrl;
        this.downloadState = downloadState;
    }

    @Generated(hash = 1263461133)
    public Movie() {
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

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDownloadState() {
        return this.downloadState;
    }

    public void setDownloadState(int downloadState) {
        this.downloadState = downloadState;
    }


}
