package com.example.hp_pk.dictionary;

/**
 * Created by root on 1/26/18.
 */

public class WordClass {
    private int id;
    private String word;
    private long usedDate;
    private boolean isFavorite;

    public WordClass() {
    }

    public WordClass(int id, String word, long usedDate, boolean isFavorite) {
        this.id = id;
        this.word = word;
        this.usedDate = usedDate;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public long getUsedDate() {
        return usedDate;
    }

    public void setUsedDate(long usedDate) {
        this.usedDate = usedDate;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
