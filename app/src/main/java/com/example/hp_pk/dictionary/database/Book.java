package com.example.hp_pk.dictionary.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @auther ZOHIDJON
 * @since 2/28/18.
 */
@Entity(nameInDb = "book")
public class Book {

    @Index(unique = true)
    String id;

    @NotNull
    String name;

    @NotNull
    String author;

    String imageUrl;

    @NotNull
    String bookUrl;

    double rate;

    long rateCount;

    String category;

    String description;

    @Generated(hash = 1884597530)
    public Book(String id, @NotNull String name, @NotNull String author,
            String imageUrl, @NotNull String bookUrl, double rate, long rateCount,
            String category, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.imageUrl = imageUrl;
        this.bookUrl = bookUrl;
        this.rate = rate;
        this.rateCount = rateCount;
        this.category = category;
        this.description = description;
    }

    @Generated(hash = 1839243756)
    public Book() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBookUrl() {
        return this.bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public double getRate() {
        return this.rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public long getRateCount() {
        return this.rateCount;
    }

    public void setRateCount(long rateCount) {
        this.rateCount = rateCount;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
