package com.example.hp_pk.dictionary.holder;

import android.content.Context;
import android.view.ViewGroup;

import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.database.Book;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * @auther ZOHIDJON
 * @since 2/28/18.
 */

public class BookViewHolder extends BaseViewHolder<Book> {

    private Context context;

    public BookViewHolder(ViewGroup parent, Context context) {
        super(parent, R.layout.row_book);
        this.context = context;
    }

    @Override
    public void setData(Book data) {
        super.setData(data);
    }
}
