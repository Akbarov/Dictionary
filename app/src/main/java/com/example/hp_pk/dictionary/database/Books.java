package com.example.hp_pk.dictionary.database;

import java.util.List;

/**
 * @auther ZOHIDJON
 * @since 2/28/18.
 */

public class Books {
    List<Book> books;

    public Books(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
