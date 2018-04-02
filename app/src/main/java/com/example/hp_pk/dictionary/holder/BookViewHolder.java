package com.example.hp_pk.dictionary.holder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.database.Book;
import com.example.hp_pk.dictionary.model.GlideApp;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * @auther ZOHIDJON
 * @since 2/28/18.
 */

public class BookViewHolder extends BaseViewHolder<Book> {

    private Context context;
    private RoundedImageView bookImage;
    private TextView bookName, bookAuthor, size, rate;

    public BookViewHolder(ViewGroup parent, Context context) {
        super(parent, R.layout.row_book);
        this.context = context;
        bookImage = $(R.id.book_image);
        bookName = $(R.id.book_name);
        bookAuthor = $(R.id.book_author);
        size = $(R.id.size);
        rate = $(R.id.rate);

    }

    @Override
    public void setData(Book data) {
        super.setData(data);
        GlideApp.with(context)
                .load(data.getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.book)
                .into(bookImage);
        bookName.setText(data.getName());
        bookAuthor.setText(data.getAuthor());
        size.setText(data.getSize());
        rate.setText(String.valueOf(data.getRate() != 0 ? data.getRate() : "4.5"));

    }

}
