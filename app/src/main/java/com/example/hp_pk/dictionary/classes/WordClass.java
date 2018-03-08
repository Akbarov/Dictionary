package com.example.hp_pk.dictionary.classes;

/**
 * @auther root
 * @since 1/26/18.
 */

public class WordClass {
    private int id;
    private int type;
    private String word;
    private String meaning;
    private long usedDate;
    private boolean isFavorite;

    public WordClass() {
    }

    public WordClass(int id, int type, String word, String meaning, long usedDate, boolean isFavorite) {
        this.id = id;
        this.type = type;
        this.word = word;
        this.meaning = meaning;
        this.usedDate = usedDate;
        this.isFavorite = isFavorite;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
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

    @Override
    public String toString() {
        return "WordClass{" +
                "id=" + id +
                ", type=" + type +
                ", word='" + word + '\'' +
                ", meaning='" + meaning + '\'' +
                ", usedDate=" + usedDate +
                ", isFavorite=" + isFavorite +
                '}';
    }
}
