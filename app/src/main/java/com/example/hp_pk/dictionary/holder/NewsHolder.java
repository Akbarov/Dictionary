package com.example.hp_pk.dictionary.holder;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.database.News;
import com.example.hp_pk.dictionary.listeners.OnNewsItemClickListener;
import com.example.hp_pk.dictionary.model.GlideApp;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * @author Zohidjon
 * @since 2018, March 26
 */

public class NewsHolder extends BaseViewHolder<News> {

    private TextView header;
    private ImageView baseImage, likeYou, share;
    private TextView likeNumber, description, date;

    private Context context;
    private OnNewsItemClickListener listener;

    public NewsHolder(ViewGroup parent, Context context, OnNewsItemClickListener listener) {
        super(parent, R.layout.news_item_layout);
        this.listener = listener;
        this.context = context;
        header = $(R.id.header);
        baseImage = $(R.id.image);
        description = $(R.id.description);
        date = $(R.id.date);
        share = $(R.id.share);
        likeNumber = $(R.id.like_number);
        likeYou = $(R.id.like_you);
    }

    @Override
    public void setData(News data) {
        super.setData(data);
        header.setText(data.getHeader());
        description.setText(data.getDescription());
        likeNumber.setText(String.valueOf(data.getLikeNumber()));
        GlideApp.with(context)
                .load(data.getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.time_edu)
                .into(baseImage);
        date.setText(DateFormat.format("yyyy-MM-dd hh:mm", data.getDate()));
        likeYou.setOnClickListener(view -> {
            if (!data.getLikeYou()) {
                data.setLikeYou(true);
                data.setLikeNumber(data.getLikeNumber() + 1);
                likeNumber.setText(String.valueOf(data.getLikeNumber()));
            }
        });
        share.setOnClickListener(view -> {
            listener.onItemClick(data,baseImage);

        });
    }

}
