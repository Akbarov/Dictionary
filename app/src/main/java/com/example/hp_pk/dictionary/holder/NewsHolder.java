package com.example.hp_pk.dictionary.holder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.database.News;
import com.example.hp_pk.dictionary.model.GlideApp;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.text.DateFormat;

/**
 * @author Zohidjon
 * @since 2018, March 26
 */

public class NewsHolder extends BaseViewHolder<News> {

    private TextView header;
    private ImageView baseImage, likeYou;
    private TextView likeNumber, description, date;

    private Context context;

    public NewsHolder(ViewGroup parent, Context context) {
        super(parent, R.layout.news_item_layout);
        this.context = context;
        header = $(R.id.header);
        baseImage = $(R.id.image);
        description = $(R.id.description);
        date = $(R.id.date);
        likeNumber = $(R.id.like_number);
        likeYou = $(R.id.like_you);
    }

    @Override
    public void setData(News data) {
        super.setData(data);
        header.setText(data.getHeader());
        description.setText(data.getDescription());
        GlideApp.with(context)
                .load(data.getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.time_edu)
                .into(baseImage);
    }
}
