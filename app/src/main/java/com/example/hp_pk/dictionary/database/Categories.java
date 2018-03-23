package com.example.hp_pk.dictionary.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

/**
 * @author Zohidjon
 * @since 2018, March 21
 */
@Entity(indexes = {@Index(value = "name,mainCategory", unique = true)})
public class Categories {

    @Property
    @NotNull
    String name;

    @NotNull
    int bookSize;
    @Property
    @NotNull
    String mainCategory;
    @Generated(hash = 81931262)
    public Categories(@NotNull String name, int bookSize,
            @NotNull String mainCategory) {
        this.name = name;
        this.bookSize = bookSize;
        this.mainCategory = mainCategory;
    }
    @Generated(hash = 267348489)
    public Categories() {
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBookSize() {
        return this.bookSize;
    }
    public void setBookSize(int bookSize) {
        this.bookSize = bookSize;
    }
    public String getMainCategory() {
        return this.mainCategory;
    }
    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }


}
