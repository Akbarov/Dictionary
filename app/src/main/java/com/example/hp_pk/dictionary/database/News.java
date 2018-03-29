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

    private boolean likeYou;

    private long date;

    @Generated(hash = 637908953)
    public News(String id, @NotNull String imageUrl, String header,
            String description, int likeNumber, boolean likeYou, long date) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.header = header;
        this.description = description;
        this.likeNumber = likeNumber;
        this.likeYou = likeYou;
        this.date = date;
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

    public boolean getLikeYou() {
        return this.likeYou;
    }

    public void setLikeYou(boolean likeYou) {
        this.likeYou = likeYou;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long date) {
        this.date = date;
    }

}
