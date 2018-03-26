package com.example.hp_pk.dictionary.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Zohidjon
 * @since 2018, March 26
 */

@Entity(nameInDb = "news")
public class News {

    @Index(unique = true)
    private String id;

    @NotNull
    private String imageUrl;

    private String header;

    private String description;

    private int likeNumber;

    @Generated(hash = 1748358093)
    public News(String id, @NotNull String imageUrl, String header,
            String description, int likeNumber) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.header = header;
        this.description = description;
        this.likeNumber = likeNumber;
    }

    @Generated(hash = 1579685679)
    public News() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikeNumber() {
        return this.likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

}
